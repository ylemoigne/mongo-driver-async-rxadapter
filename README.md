# mongo-driver-async-rxadapter
RxJava adapter for Mongo 3 async driver

Usage
------
    new RxMongoClient(mongoClient)


Dependency.
------

Maven

    <dependency>
      <groupId>fr.javatic.mongo</groupId>
      <artifactId>mongo-driver-async-rxadapter</artifactId>
      <version>3.0.0-beta3__0.1</version>
      <scope>compile</scope>
    </dependency>

Gradle

    repositories {
        maven {
            url "http://dl.bintray.com/ylemoigne/maven"
        }
    }

    dependencies {
        compile 'fr.javatic.mongo:mongo-driver-async-rxadapter:3.0.0-beta3__0.1'
    }

Changelog.
------
0.1   : Initial Release
0.2   : Fixed Void callback to return empty Observable (previous returned a single item Observable with null value)
0.3   : Add wrappers to top level mongo (RxMongoClient)
0.3.1 : Replace most of Observable<Void> by Observable with main parameter type and return the parameter type