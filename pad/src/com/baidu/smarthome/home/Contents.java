
package com.baidu.smarthome.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contents {

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static HashMap<String,List<Talk>> testData;

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "dad"));
        addItem(new DummyItem("2", "mom"));
        addItem(new DummyItem("3", "son"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<Talk> getTestData(String name){
        if (testData == null) {
            testData = new HashMap<String,List<Talk>>();
            List<Talk> toSun = new ArrayList<Talk>();
            toSun.add(new Talk("妈妈", "打酱油"));
            toSun.add(new Talk("爸爸", "打酱油"));
            toSun.add(new Talk("奶奶", "打酱油"));
            toSun.add(new Talk("弟弟", "打酱油"));
            testData.put("dad", toSun);
        }
        return testData.get(name);
    }
}
