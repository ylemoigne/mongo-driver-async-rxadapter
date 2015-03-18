/*
 * Copyright 2015 Yann Le Moigne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.javatic.mongo.rxadapter;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientOptions;
import org.bson.Document;

public class RxMongoClient {
    private final MongoClient delegate;

    public RxMongoClient(MongoClient delegate) {
        this.delegate = delegate;
    }

    public RxMongoDatabase getDatabase(String name) {
        return new RxMongoDatabase(delegate.getDatabase(name));
    }

    public void close() {
        delegate.close();
    }

    public MongoClientOptions getOptions() {
        return delegate.getOptions();
    }

    public RxMongoIterable<String> listDatabaseNames() {
        return new RxMongoIterable<>(delegate.listDatabaseNames());
    }

    public RxListDatabasesIterable<Document> listDatabases() {
        return new RxListDatabasesIterable<>(delegate.listDatabases());
    }

    public <TResult> RxListDatabasesIterable<TResult> listDatabases(Class<TResult> resultClass) {
        return new RxListDatabasesIterable<>(delegate.listDatabases(resultClass));
    }
}
