relations  = set()

def generate_relations():
    with open("../DatasetOfSensors/trainWithName.txt") as f:
        for line in f:
            elements=line.split("\t")
            if(len(elements)>=2):
                relations.add(elements[1])
    
    with open("../DatasetOfSensors/testWithName.txt") as f:
        for line in f:
            elements=line.split("\t")
            if (len(elements) >= 2):
                relations.add(elements[1])
    
    with open("../DatasetOfSensors/validWithName.txt") as f:
        for line in f:
            elements=line.split("\t")
            if (len(elements) >= 2):
                relations.add(elements[1])
    
    file2 = open("../DatasetOfSensors/relation2id.txt","w+")
    num=0
    for i in relations:
        file2.write(i+"\t"+str(num)+"\n")
        num=num+1
    file2.close()
