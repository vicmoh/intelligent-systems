CIS*3700 Assignment 3
Kyle Hersey
0851973

Compilation:
    javac DTLearn.java

Execution:
    java DTLearn <schemefile> <datafile>

Test Environment:
    Darwin 10.13.3 (MacBookAir6,2)
    java version "1.8.0_144"
    Java(TM) SE Runtime Environment (build 1.8.0_144-b01)
    Java HotSpot(TM) 64-Bit Server VM (build 25.144-b01, mixed mode)

Sample Ouput:
Learning Starts:
        Test alternate: gain = 0.0
        Test waitArea: gain = 0.0
        Test friOrSat: gain = 0.014362591564146543
        Test hungry: gain = 0.1356555774109669
        Test customer: gain = 0.3748900964125389
        Test price: gain = 0.1356555774109669
        Test raining: gain = 0.014362591564146543
        Test reserved: gain = 0.014362591564146543
        Test foodType: gain = 0.0
        Test waitTime: gain = 0.1438410362258905
                Select attribute customer
        Test alternate: gain = 0.07567111245376568
        Test waitArea: gain = 0.0
        Test friOrSat: gain = 0.07567111245376568
        Test hungry: gain = 0.17441604792151594
        Test price: gain = 0.17441604792151594
        Test raining: gain = 0.030575011695625487
        Test reserved: gain = 0.17441604792151594
        Test foodType: gain = 0.17441604792151594
        Test waitTime: gain = 0.17441604792151594
                Select attribute hungry
        Test alternate: gain = 0.0
        Test waitArea: gain = 0.0
        Test friOrSat: gain = 0.2157615543388357
        Test price: gain = 0.2157615543388357
        Test raining: gain = 0.2157615543388357
        Test reserved: gain = 0.2157615543388357
        Test foodType: gain = 0.34657359027997264
        Test waitTime: gain = 0.0
                Select attribute foodType
        Test alternate: gain = 0.0
        Test waitArea: gain = 0.0
        Test friOrSat: gain = 0.6931471805599453
        Test price: gain = 0.0
        Test raining: gain = 0.6931471805599453
        Test reserved: gain = 0.0
        Test waitTime: gain = 0.6931471805599453
                Select attribute friOrSat

Printing Decision Tree:
> root
------> customer
  > NONE
  ------> [NO]
  > SOME
  ------> [YES]
  > FULL
  ------> hungry
    > YES
    ------> foodType
      > FRENCH
      ------> [YES]
      > THAI
      ------> friOrSat
        > YES
        ------> [YES]
        > NO
        ------> [NO]
      > BURGER
      ------> [YES]
      > ITALIAN
      ------> [NO]
    > NO
    ------> [NO]


