# CloudComputingProject
Analyse census and university data-sets to identify the university as to how well serves the community

Team members: 
Shambhangoudar, Pratibha V     PVS10@pitt.edu 
Osterritter, Luke              luo4@pitt.edu 
Bian, Dalun                    dab217@pitt.edu 

There are 5 MapReduce java program and one regular java program
make sure hadoop 2.6.0 and jdk 1.7 are installed and properly configured
MapReduce program:

Start the hadoop:
start-all.sh

First in hdfs we need to create the input path and put the census data into hdfs
run:
hdfs dfs -mkdir /usr/hadoop/data
hdfs dfs -put /YOUR_PATH/census-income.data /usr/hadoop/data

Then complie java file to get classes
run:
javac -classpath `yarn classpath` -d . ****.java 

complie classes into jar
jar cf **.jar ALL_CLASS_NAME.class

run in hadoop
hadoop jar **.jar  NAME_OF_JOBDriver /usr/hadoop/data/census-income.data /usr/hadoop/output

ps: all the main function end in Driver for example if want to run for education rate change NAME_OF_JOB into EducationRate

After that we can get the output data in /usr/hadoop/output named part-r-00000

stop hadoop:
stop-all.sh

Regular java program:
Move all the output file into the ROOT_OF_PROJECT/data of the java project
and rename them:
averageincome.data, educationrate.data, malefemale.data, uneducatedpeople.data university.data

set the args of test.java into ./data/university.data ./data/averageincome.data ./data/educationrate.data ./data/malefemale.data ./data/uneducatedpeople.data

run test.java

the final scores will show on the console.



