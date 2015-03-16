# mongo-driver-async-rxadapter
RxJava adapter for Mongo 3 async driver

Usage
------
new RxMongoCollection<>(myMongoCollection)


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
