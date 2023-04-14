entities_set  = set()
categories=dict()
entity_categories=dict()

def generate_entities():
    with open("../DatasetOfSensors/trainWithName.txt") as f:
        for line in f:
            elements = line.split("\t")
            if (len(elements) >= 2):
                entities_set.add(elements[0].replace("\n",""))
                entities_set.add(elements[2].replace("\n",""))
    f.close()
    with open("../DatasetOfSensors/testWithName.txt") as f:
        for line in f:
            elements = line.split("\t")
            if (len(elements) >= 2):
                entities_set.add(elements[0].replace("\n",""))
                entities_set.add(elements[2].replace("\n",""))
    f.close()
    with open("../DatasetOfSensors/validWithName.txt") as f:
        for line in f:
            elements = line.split("\t")
            if (len(elements) >= 2):
                entities_set.add(elements[0].replace("\n",""))
                entities_set.add(elements[2].replace("\n",""))
    
    f.close()
    with open("../DatasetOfSensors/category2id.txt") as f:
        for line in f:
            elements=line.split("\t")
            categories[elements[0]]=elements[1]
    
    f.close()
    
    with open("../DatasetOfSensors/categories.txt") as f:
        for line in f:
            elements=line.split("\t")
            entity_categories[elements[0]]=categories[elements[2].replace("\n","")]
    
    f.close()
    file2 = open("../DatasetOfSensors/entityNames.txt","w+")
    file3 = open("../DatasetOfSensors/entity2id.txt","w+")
    file4 = open("../DatasetOfSensors/entity_categories.txt","w+")
    
    num=0
    num_id = 1000000
    
    for i in entities_set:
        file2.write(str(i)+"\t"+str(num)+"\n")
        file3.write(str(num_id)+"\t"+str(num)+"\n")
        #print(i)
        categoryOfEntity=entity_categories[i].replace("\n","")
        file4.write(str(num_id-1000000)+"\t"+str(categoryOfEntity)+"\n")
        num=num+1
        num_id=num_id+1
    
    file2.close()
