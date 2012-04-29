import sys            # Command-line arguments, etc.
import metaweb        # Metaweb services

'''
Query for a person using his/her Freebase guid (default is Darwin's), and retrieve his/her properties
and connections. 
Properties retrived: name, date of birth, place of birth. 
Yet to be retrieved: gender, places_lived, profession, religion
Darwin's Freebase guid: #9202a8c04000641f800000000000cb7c
'''

# Create a session object
freebase = metaweb.Session("api.freebase.com")

# keys are relationship types returned by a person query;
# some relationships link to relationship pages instead of person page;
# values are the relationship type to look for in a relationship page and 
# the name of the field containing the names of the two connected people.
# NOTE: do not follow Influenecd and Influenced By connections.
SEARCH_CONNECTIONS = {
	"/people/person/parents" : {"relation_type": None, "fieldname": None},
	"/people/person/sibling_s" : {"relation_type": "/people/sibling_relationship", "fieldname": "sibling"}, 
	"/people/person/children" : {"relation_type": None, "fieldname": None}, 
	"/people/person/spouse_s" : {"relation_type": "/people/marriage", "fieldname": "spouse"}, 
	"/education/academic/advisors" : {"relation_type": None, "fieldname": None}, 
	"/education/academic/advisees" : {"relation_type": None, "fieldname": None}, 
	"/influence/influence_node/peers" : {"relation_type": "/influence/peer_relationship", "fieldname": "peers"},
	#'/influence/influence_node/influenced': {"relation_type": None, "fieldname": None},
	#'/influence/influence_node/influenced_by': {"relation_type": None, "fieldname": None}
}

def query_person_by_guid(person_guid='#9202a8c04000641f800000000000cb7c'):
	# Our MQL query in Python: fields with value 'None' are to be populated
	# by query results
	
	# construct the query by starting with the property fields to look for...
	query = {
		'type': '/people/person',
		'guid': person_guid,
		'name': None,
		'date_of_birth': None,
		'place_of_birth': None,
	}
	
	# ...now define the connection fields and add them
	query_connections = { 
		query_field_name: [{'name':None, 'guid': None, 'optional': True}] 
		for query_field_name in SEARCH_CONNECTIONS.keys()
	}
	query.update(query_connections)
	
	# Submit query, get results
	result = freebase.read(query)
	if result==None:
		print ">>> ERROR: No results for GUID %s; check GUID or type of this GUID" % person_guid
		return None, None
	
	# Return properties
	properties = _extract_properties_from_results(result)
	#print properties # debug
	
	# Return connections
	connections = _extract_connections_from_results(result)
	#print connections # debug
	
	return connections, properties


def _find_relationship_target(relation_guid, relation_type, name_src):
	'''
	Given the GUID of relationship page and the source, find the target and
	return its name and GUID.
	E.g., for the relationship (Charles Darwin, Alfred Russel Wallace)
	with Darwin as source, this function willl return Wallace's name and GUID.
	TODO: find the GUID (scrape from page?) without using the name.  
	'''
	key_to_names_of_people_in_relationship = SEARCH_CONNECTIONS[relation_type]['fieldname']
	
	query = {
		"guid" : relation_guid,
		"type" : SEARCH_CONNECTIONS[relation_type]['relation_type'],
		key_to_names_of_people_in_relationship : []
	}
	
	# Submit query, get results
	result = freebase.read(query)
	if result==None:
		return None, None
	
	# a relationship page contains two names; find the one different than the
	# source. If no such name exists, return None, None
	name_tgt = None
	for name in result[key_to_names_of_people_in_relationship]: 
		if name!=name_src:
			name_tgt = name
			break
	if name_tgt is None:
		return None, None
	
	# Now query for the target's name to find its guid.
	query = [{
		"name": name_tgt,
		"type": "/people/person",
		"guid": None
	}]
	result = freebase.read(query)
	if len(result)!=1:
		# No results, or too many results so we can't figure the correct person 
		# print ">>> Too many %s (%s) !" % (name_tgt, len(result)) # debug
		return None, None
	
	# All is well, return name and guid
	return name_tgt, result[0]['guid'] # The only item on the list


def _extract_properties_from_results(results):
	'''
	Return a dictionary of properties. Assume results is non-null.
	'''
	properties = {}
	
	# Use blank string instead of None - makes joining easier
	properties['name'] =  _normalize_strings(results['name']) # TODO: crashed here! on ID #9202a8c04000641f80000000002203ce
	properties['date_of_birth'] = _normalize_strings(results['date_of_birth'])
	properties['place_of_birth'] = _normalize_strings(results['place_of_birth'])
	
	return properties


def _normalize_strings(st):
	# Convert to utf-8 and replace None with a blank string to faciliate join()
	return st.encode('utf-8') if st!=None else ''


def _extract_connections_from_results(results):
	'''
	Return a list containing guid, name, relationship type for each connection.
	Assume results is non-null.
	
	Example:
	{'conn_type': 'children',
	 'guid': '#9202a8c04000641f80000000002f3ad8',
	 'name': 'Charles Waring Darwin'},
	'''
	relationship_source_name = results['name']
	
	connections = []
	
	for field_name, values_list in results.iteritems():
		if field_name not in SEARCH_CONNECTIONS.keys():
			# field_names contain relationships and other properties,
			# use only the former
			continue
			
		relationship_type = field_name # easier to understand...
		# Add connections of all sought relationship type to the list
		for target in values_list:
			if target['name']==None:
				# Fix broken links: some Freebase links lead to a "relationship
				# page" instead of a "person page", which results in the name of
				# the target person showing up as None. However, a connection 
				# page does list names and GUIDs of the two people connected in
				# other fields -- this code tries to retrieve it.
				## debug ##
				# print ">>> Missing name for GUID %s " % target['guid']
				# print ">>> Trying to locate using source %s" % relationship_source_name
				
				target['name'], target['guid'] = \
					_find_relationship_target(target['guid'], relationship_type, \
					relationship_source_name)
				# print ">>> Found: %s %s (%s)" % (target['name'], \
				#	target['guid'], relationship_type) # debug
			
			if target['name']!=None:
				# If person already existed or was just found, add to list of
				# connections and add the shortened relationship type
				target['conn_type'] = relationship_type.split('/')[-1]
				connections.append(target)
				# print connections # debug
			
	return connections


def get_network_from_seed(seed_guid='#9202a8c04000641f800000000000cb7c', max_num_of_nodes=100):
	'''
	Create a network used passed guid as seed (Charles Darwin is default),
	and add the connections of up to max_num_of_nodes people (100 is default).
	'''
	nodefile = open('nodefile.csv', 'w')
	edgefile = open('edgefile.csv', 'w')
	
	search_set = [] # can't iterate on a mutating set, must be a list
	search_set.append(seed_guid) # Darwin's is #9202a8c04000641f800000000000cb7c
	
	try:
		for i, person_guid in enumerate(search_set):
			# TODO: May want to add a random timeout between iterations
			# so we don't annoy Freebase.
		
			print "******" 			# debug
			print "Iteration: %s guid: %s Items in search set: %s" \
				% (i, person_guid, len(search_set))# debug
			print "******" 			# debug
		
			try:
				connection_list, properties = query_person_by_guid(person_guid)
			except:
				# problem with Query, move on
				print "ERROR:", sys.exc_info()[0]
				continue
			
			if not properties:
				# Something went wrong, move on
				continue
			# print ">>>>>>>>", properties # debug
			
			# serialize properties to nodefile
			#print properties # debug
			nodefile.write(person_guid + '\t' + '\t'.join(properties.values()) + '\n') 
		
			# serialize connections to edgefile
			for connection in connection_list:
				print connection['guid'], connection['name'], connection['conn_type']
				name = _normalize_strings(connection['name'])
				
				
				edgefile.write(person_guid + '\t' + properties['name'] + '\t' + \
					connection['guid'] + '\t' + str(name) + '\t' + \
					str(connection['conn_type']) + '\n') 
				if connection['guid'] not in search_set:
					search_set.append(connection['guid'])
					
			if max_num_of_nodes>0 and i+1>=max_num_of_nodes:
				# Place a hard limit on the number of nodes.
				# 0 for unlimited TODO: 1 will actually go 
				print "Stopping: limit set to %d iteration(s)" % max_num_of_nodes
				break
				
	finally:
		nodefile.close()
		edgefile.close()
	
	return len(search_set)


def convert_date_to_year(in_nodefile, out_nodefile):
	'''
	Convert the values in the date of birth column to years,
	with optional range filter
	'''
	fin = open(in_nodefile, 'rU')
	fout = open(out_nodefile, 'w')
		
	for line in fin:
		cell_values = line[:-1].split('\t')
		# the second cell holds the date.
		# date format is yyyy-mm-dd, we only need the first
		cell_values[1] = cell_values[1].split('-')[0]
		fout.write('\t'.join(cell_values) + '\n')


def filter_by_year(in_nodefile, out_nodefile, start_year=-10000, end_year=10000):
	'''
	Filter people by year. Must convert full dates to year first!
	'''
	fin = open(in_nodefile, 'rU')
	fout = open(out_nodefile, 'w')
	
	print "Getting only people born between", start_year, "and", end_year
	
	for line in fin:
		try:
			# the second cell holds the year.
			year = int(line.split('\t')[1])
		except ValueError:
			# No birth date
			continue
		
		if year>=start_year and year<=end_year:
			fout.write(line) # includes EOL
			
if __name__ == "__main__":
	get_network_from_seed(seed_guid='#9202a8c04000641f800000000000cb7c', max_num_of_nodes=0)
	