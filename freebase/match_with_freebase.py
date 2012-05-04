import freebase_people_network
import time

class Match:
	ALL = 0
	FIRST_LAST = 1

def match_table_with_freebase(match_type, infile, outfile, 
	name_col, birth_col, death_col):
	'''
	Given tab-delimited table containing people's names and (optionally) their 
	year of birth year of death, create a new file with the Freebase name, GUID,
	and dates of possible matches for each person to original name. 
	Matching by querying all of parts of a person's name (Match.ALL) or just 
	first name and last name (Match.FIRST_LAST). 
	name_col, birth_col, death_col are the column holding name, year of birth,
	year of death, respectively.
	'''
	fin = open(infile, 'rU')
	fout = open(outfile, 'w')
	
	fin.readline() # skip header
	
	total_matches = 0
	
	for i, line in enumerate(fin):
		splt = line[:-1].split('\t')
		name = splt[name_col]
		try: # this value is optional
			year_of_birth = int(splt[birth_col])
		except ValueError:
			year_of_birth = None
		try: # this value is optional
			year_of_death = int(splt[death_col])
		except ValueError:
			year_of_death = None
		
		# split name, use only first word and last word 
		name_parts = name.split(' ')

		if match_type==Match.FIRST_LAST:
			name_parts_to_query = [name_parts[0], name_parts[-1]]
		else:
			name_parts_to_query = name_parts

		# debug
		#print 'searching for: %s %s %s' % \
		#	(name_parts_to_query, str(year_of_birth), str(year_of_death))

		possible_matches = \
			freebase_people_network.find_person_by_name_and_dates(
			name_parts_to_query, year_of_birth, year_of_death)
			
		# Write results: all possible matches for same person go in the same 
		# line, prefixed with original name to make matching easier
		output = [name]
		if possible_matches:
			for match in possible_matches:
				try:
					# add the parameters returned from Freebase
					output.append(match['name'].encode('utf-8'))
					output.append(match['guid'])
					output.append(str(match['date_of_birth']))
					output.append(str(match['/people/deceased_person/date_of_death']))

					#end([ value.decode('utf-8') for value in possible_matches[0].itervalues() ])
				except:
					# this should have been fixed by now
					print output
					print "aborting!"
					return None
			print 'Line %d: Found %d match(es) for %s (%s-%s)!' % \
				(i, len(possible_matches), name.decode('utf-8'), str(year_of_birth), \
				str(year_of_death))
			total_matches += 1
		else:
			pass
			#output.extend(['', '','','',''])
			#print ">>> ERROR: No results / too many results for name %s, \
			#birth %s, death %s" % (name_parts_to_query, str(year_of_birth), \ 
			#str(year_of_death)) # debug			
		
		# write the line to new file, whether augmented with FB data or not
		fout.write('\t'.join(output) + '\n')
		# print '*********'
	
	print ">>> Total matches: %d/%d" % (total_matches, i+1)
	fout.close()


if __name__ == "__main__":
	'''
	start = time.time()
	match_table_with_freebase(Match.FIRST_LAST, '../../rebeca_jia_cleanup/jia_rebeca_nodes.txt', '../../rebeca_jia_cleanup/jia_rebeca_nodes_fb_first_last_new.txt', name_col=2, birth_col=3, death_col=4)
	print "%d seconds" % (time.time()-start)
	'''

	start = time.time()	
	match_table_with_freebase(Match.ALL, '../../yingxiang/yingxiang_nodes.txt', '../../yingxiang/yingxiang_nodes_fb_all.txt', name_col=1, birth_col=2, death_col=3)
	print "%d seconds" % (time.time()-start)