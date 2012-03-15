import sys            # Command-line arguments, etc.
import metaweb        # Metaweb services


# Maximum number of people whose connections we retrieve. 
# May increase as the code improves.
MAX_NUM_OF_PEOPLE = 5

'''
Query for a person using his/her Freebase guid (default is Darwin's), and retrieve his/her properties
and connections. 
Properties retrived: name, date of birth, place of birth. 
Yet to be retrieved: gender, places_lived, place_of_birth, profession, religion
Darwin's Freebase guid: #9202a8c04000641f800000000000cb7c
'''
def query_person_by_guid(person_guid='#9202a8c04000641f800000000000cb7c'):	
	# Our MQL query in Python: fields with value 'None' are to be populated
	# by query results
	query = {'type': '/people/person',
		'guid': person_guid,
		'name': None,
		'date_of_birth': None,
		'place_of_birth': None,
		'/influence/influence_node/influenced_by': [{'name':None, 'guid': None}],
		'/influence/influence_node/peers': [{'name':None, 'guid': None}],
		'/influence/influence_node/influenced': [{'name':None, 'guid': None}],
		"/people/person/parents": [{'name':None, 'guid': None}],
		"/people/person/sibling_s": [{'name':None, 'guid': None}],
		"/people/person/children": [{'name':None, 'guid': None}],
		"/people/person/spouse_s": [{'name':None, 'guid': None}],
	}
	
	# Create a session object
	freebase = metaweb.Session("api.freebase.com")
	
	# Submit query, get results
	result = freebase.read(query)
	
	# Return properties
	properties = extract_properties_from_results(result)
	#print properties # debug
	
	# Return connections
	connections = extract_connections_from_results(result)
	#print connections # debug
	
	return connections, properties


'''
Return a dictionary of properties
'''
def extract_properties_from_results(results):
	properties = {}
	
	if results:
		properties['name'] = results['name']
		properties['date_of_birth'] = results['date_of_birth']
		properties['place_of_birth'] = results['place_of_birth']
	
	return properties


'''
Return a list of tuples: (guid, relationship type)
'''
def extract_connections_from_results(results):
	connections = []
	
	if results:
		# Add connected people of all available relationship types
		# to the list, adding the type of the relationship to each
		# connection as well.
		connections.extend( create_people_list(results,\
			'/influence/influence_node/influenced_by') )
		connections.extend( create_people_list(results,\
			'/influence/influence_node/peers') )
		connections.extend( create_people_list(results,\
			'/influence/influence_node/influenced') )
		connections.extend( create_people_list(results,\
			'/people/person/parents') )
		connections.extend( create_people_list(results,\
			'/people/person/sibling_s') )
		connections.extend( create_people_list(results,\
			'/people/person/children') )
		connections.extend( create_people_list(results,\
			'/people/person/spouse_s') )
			
	return connections


def create_people_list(result_dict, full_conn_type):
	# get a result dictionary returned by our query and populate a list of
	# people. Assume dictionary contains name and guid, and add type of
	# connection (conn_type)
		
	list_of_people = []
		
	for dude in result_dict[full_conn_type]:
		short_conn_type = full_conn_type.split('/')[-1]
		#print "short conn type:", short_conn_type
		dude['conn_type'] = short_conn_type
		list_of_people.append(dude)
		#print "dude's name:", dude
		
	return list_of_people


def get_network_from_seed(seed_guid='#9202a8c04000641f800000000000cb7c'):
	
	nodefile = open('nodefile.csv', 'w')
	edgefile = open('edgefile.csv', 'w')
	
	search_set = [] # can't iterate a mutating set, must be a list
	search_set.append(seed_guid) # Darwin's is #9202a8c04000641f800000000000cb7c
	
	for i, person_guid in enumerate(search_set):
		# TODO: May want to add a random timeout between iterations
		# so we don't annoy Freebase.
		
		print "******" 			# debug
		print "Iteration:", i, "guid:", person_guid, \
			"Items in search set:",  len(search_set)	# debug
		print "******" 			# debug
		if i>MAX_NUM_OF_PEOPLE:
			# Hardstop for debugging: better make sure we get a small number
			# of correct results before we harvest the entire site.
			break
		
		connection_list, properties = query_person_by_guid(person_guid)
		
		# serialize properties to nodefile
		print properties # debug
		nodefile.write(person_guid + '\t' + '\t'.join(properties.values()) + '\n') 
		
		# serialize connections to edgefile
		for connection in connection_list:
			print connection['guid'], connection['name'], connection['conn_type']
			name = None if connection['name']==None else connection['name'].encode('utf-8')
			edgefile.write(person_guid + '\t' + properties['name'] + '\t' + \
				connection['guid'] + '\t' + str(name) + '\t' + \
				str(connection['conn_type']) + '\n') 
			if connection['guid'] not in search_set:
				search_set.append(connection['guid'])
			
			# TODO: add counter for number of connections
				
	nodefile.close()
	edgefile.close()
	
	return len(search_set)

