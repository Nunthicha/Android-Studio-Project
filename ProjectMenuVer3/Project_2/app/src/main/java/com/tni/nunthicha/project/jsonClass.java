package com.tni.nunthicha.project;

public class jsonClass {
    private String content = "";
    private String root = "\"not set\":";

    public jsonClass setRoot(String name) {
        root = "\"" + name + "\":";
        return this;
    }

    public jsonClass addString(String name, String text) {
        if (content != "") {
            content += ", ";
        }
        content += "\"" + name + "\":" + "\"" + text + "\"";
        //System.out.println(content);
        return this;
    }

    public String get() {
        return root + "{" + content + "}";
    }
}
