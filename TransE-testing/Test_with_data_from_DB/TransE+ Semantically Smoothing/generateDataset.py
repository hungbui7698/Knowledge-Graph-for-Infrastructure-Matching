
entities=dict()
idNums=[]
entitiesName=[]

def generate_dataset():
    with open("../DatasetOfSensors/entity2id.txt") as f:
        for line in f:
            elements = line.split("\t")
            idNums.append(str(elements[0]))
    
    f.close()
    
    with open("../DatasetOfSensors/entityNames.txt") as f:
        for line in f:
            elements = line.split("\t")
            entitiesName.append(str(elements[0]))
    
    for i in range(len(entitiesName)):
        entities[entitiesName[i]]=idNums[i]
    
    file2 = open("../DatasetOfSensors/test.txt","w+")
    
    with open("../DatasetOfSensors/testWithName.txt") as f:
        for line in f:
            elements = line.split("\t")
            if (len(elements) >= 2):
                headId=entities[elements[0]]
                #print(headId)
                relationName=elements[1]
                #print(elements[1])
                tailId=entities[elements[2].replace("\n","")]
                file2.write(headId + "\t" + tailId + "\t"+relationName+"\n")
#test adding test data
        headId=entities["UserApp_Test"]
        relationName1="UserApp_require_Network_Slice"
        tailId1=entities["UserApp_Test"]
        file2.write(headId + "\t" + tailId1 + "\t"+relationName1+"\n")

        relationName2="UserApp_require_Cloud_Slice"
        tailId2=entities["UserApp_Test"]
        file2.write(headId + "\t" + tailId2 + "\t"+relationName2+"\n")

        #relationName3="UserApp_require_App_Instance"
        #tailId3=entities["UserApp_Test"]
        #file2.write(headId + "\t" + tailId3 + "\t"+relationName3+"\n")
    f.close()
    
    file2 = open("../DatasetOfSensors/train.txt","w+")
    
    with open("../DatasetOfSensors/trainWithName.txt") as f:
        for line in f:
            elements = line.split("\t")
            if (len(elements) >= 2):
                headId=entities[elements[0]]
                relationName=elements[1]
                tailId=entities[elements[2].replace("\n","")]
                file2.write(headId + "\t" + tailId + "\t"+relationName+"\n")
    f.close()
    
    file2 = open("../DatasetOfSensors/valid.txt","w+")
    
    with open("../DatasetOfSensors/validWithName.txt") as f:
        for line in f:
            elements = line.split("\t")
            if (len(elements) >= 2):
                headId=entities[elements[0]]
                relationName=elements[1]
                tailId=entities[elements[2].replace("\n","")]
                file2.write(headId + "\t" + tailId + "\t"+relationName+"\n")
    f.close()
