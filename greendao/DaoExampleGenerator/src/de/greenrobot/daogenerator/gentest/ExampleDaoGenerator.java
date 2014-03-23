/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
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
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.baidu.smarthome.phone.dao");

        setup(schema);
        
        new DaoGenerator().generateAll(schema, "src-gen");
    }

    private static void setup(Schema schema) {
        Entity member = schema.addEntity("Member");
        member.addIdProperty().primaryKey().autoincrement();
        member.addStringProperty("name").notNull();
        member.addBooleanProperty("athome");
        member.addDoubleProperty("longitude");
        member.addDoubleProperty("lattitude");
        member.addStringProperty("position");
        member.addIntProperty("avatarid");
        
        Entity message = schema.addEntity("Message");
        message.addIdProperty().primaryKey().autoincrement();
        message.addStringProperty("cotent").notNull();
        Property peerId = message.addLongProperty("peerid").notNull().getProperty();
        message.addBooleanProperty("peermsg").notNull();
        Property msgDate = message.addDateProperty("timestamp").notNull().getProperty();
        
        ToMany peerMsgHistory = member.addToMany(message, peerId);
        peerMsgHistory.setName("messagelist");
        peerMsgHistory.orderAsc(msgDate);
    }
}
