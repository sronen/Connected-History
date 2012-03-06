import sys            # Command-line arguments, etc.
import metaweb        # Metaweb services


def create_people_list(result_dict, full_conn_type):
	# get a result dictionary returned by our query and populate a list of
	# people. Assume dictionary contains name and guid, and add type of
	# connection (conn_type)
	
	list_of_people = []
	
	for dude in result_dict[full_conn_type]:
		short_conn_type = full_conn_type.split('/')[-1]
		print "short conn type:", short_conn_type
		dude['connection_to_d'] = short_conn_type
		list_of_people.append(dude)
		print "dude's name:", dude
		
	return list_of_people


def get_connected_people(person_name):
	
	query = {'type': '/people/person',       # Our MQL query in Python.
		'name': person_name,                  # Place the band in the query.
		'/influence/influence_node/influenced_by': [{'name':None, 'guid': None}],
		'/influence/influence_node/peers': [{'name':None, 'guid': None}],
		'/influence/influence_node/influenced': [{'name':None, 'guid': None}],
		"/people/person/parents": [{'name':None, 'guid': None}],
		"/people/person/sibling_s": [{'name':None, 'guid': None}],
		"/people/person/children": [{'name':None, 'guid': None}],
		"/people/person/spouse_s": [{'name':None, 'guid': None}]
	}
	
	'''
	query = {'type': '/people/person',       # Our MQL query in Python.
		'name': person_name,                  # Place the band in the query.
		'/influence/influence_node/influenced_by': [{'name':None, 'guid': None}]
		}
	'''
	
	freebase = metaweb.Session("api.freebase.com") # Create a session object
	result = freebase.read(query)                 # Submit query, get results

	people = []

	if result:                                     # If we got a result
		print "Darwin's  by %s:" % result['name']    # print the album names
		#print "\n".join([influenced_by['name'] for influenced_by in result['/influence/influence_node/influenced_by']])
		
		people.extend( create_people_list(result,\
			'/influence/influence_node/influenced_by') )
		people.extend( create_people_list(result,\
			'/influence/influence_node/peers') )
		people.extend( create_people_list(result,\
			'/influence/influence_node/influenced') )
		people.extend( create_people_list(result,\
			'/people/person/parents') )
		people.extend( create_people_list(result,\
			'/people/person/sibling_s') )
		people.extend( create_people_list(result,\
			'/people/person/children') )
		people.extend( create_people_list(result,\
			'/people/person/spouse_s') )
			
		
		print people
		'''
		for dude in result['/influence/influence_node/influenced_by']:
			dude['connection_to_d'] = 'influenced_by'
			d_influenced_by.append(dude)
		
			print dude
		'''
		
		'''
		person_connections = \
			result['/influence/influence_node/influenced_by'] + \
			result['/influence/influence_node/peers'] + \
			result['/influence/influence_node/influenced'] + \
			result['/people/person/parents'] + \
			result['/people/person/sibling_s'] + \
			result['/people/person/children'] + \
			result['/people/person/spouse_s']
			
		print person_connections
		'''
	
	else:
		print  "No item %s found", person
		
	return people


'''
properties:
date_of_birth
gender
places_lived
place_of_birth
profession
religion

guid --> Darwin's is  #9202a8c04000641f800000000000cb7c

potential links:
children
parents
/influence/influence_node/influenced_by
/influence/influence_node/peers
/influence/influence_node/influenced

'''