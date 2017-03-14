package io.sightly.tck.html;

import org.junit.Assert;
import org.junit.Test;

public class HTMLExtractorTest {

    @Test
    public void testHasAttribute() {

        class Combination {
            public String url;
            public String markup;
            public String selector;
            public boolean exists;
            public String attributeName;
            public String attributeValue;

            public boolean expectedTrue;

            public Combination(String url,
                    String markup,
                    String selector,
                    boolean exists,
                    String attributeName,
                    String attributeValue,
                    boolean expectedTrue) {
                this.url = url;
                this.markup = markup;
                this.selector = selector;
                this.exists = exists;
                this.attributeName = attributeName;
                this.attributeValue = attributeValue;
                this.expectedTrue = expectedTrue;
            }
        }

        Combination[] combinations = new Combination[] {
                new Combination("t01", "<div id=\"test\" title=\"something\"></div>", "#test", true, "title", "", true),
                new Combination("t01", "<div id=\"test\" title=\"something\"></div>", "#test", true, "title", null, true),
                new Combination("t01", "<div id=\"test\" title=\"something\"></div>", "#test", true, "title", "something", true),
                new Combination("t01", "<div id=\"test\" title=\"something\"></div>", "#test", false, "title", "", false),
                new Combination("t01", "<div id=\"test\" title=\"something\"></div>", "#test", false, "title", null, false),
                new Combination("t01", "<div id=\"test\" title=\"something\"></div>", "#test", false, "title", "something", false),

                new Combination("t02", "<div id=\"test\" title=\"\"></div>", "#test", true, "title", "", true),
                new Combination("t02", "<div id=\"test\" title=\"\"></div>", "#test", true, "title", null, true),
                new Combination("t02", "<div id=\"test\" title=\"\"></div>", "#test", true, "title", "something", false),
                new Combination("t02", "<div id=\"test\" title=\"\"></div>", "#test", false, "title", "", false),
                new Combination("t02", "<div id=\"test\" title=\"\"></div>", "#test", false, "title", null, false),
                new Combination("t02", "<div id=\"test\" title=\"\"></div>", "#test", false, "title", "something", false),

                new Combination("t03", "<div id=\"test\" title></div>", "#test", true, "title", "", true),
                new Combination("t03", "<div id=\"test\" title></div>", "#test", true, "title", null, true),
                new Combination("t03", "<div id=\"test\" title></div>", "#test", true, "title", "something", false),
                new Combination("t03", "<div id=\"test\" title></div>", "#test", false, "title", "", false),
                new Combination("t03", "<div id=\"test\" title></div>", "#test", false, "title", null, false),
                new Combination("t03", "<div id=\"test\" title></div>", "#test", false, "title", "something", false),

                new Combination("t04", "<div id=\"test\"></div>", "#test", false, "title", null, true),
                new Combination("t04", "<div id=\"test\"></div>", "#test", false, "title", "", true),
                new Combination("t04", "<div id=\"test\"></div>", "#test", false, "title", "something", true),
                new Combination("t04", "<div id=\"test\"></div>", "#test", true, "title", null, false),
                new Combination("t04", "<div id=\"test\"></div>", "#test", true, "title", "", false),
                new Combination("t04", "<div id=\"test\"></div>", "#test", true, "title", "something", false),
        };

        for (Combination c : combinations) {
            String message = String.format("Expected %s when looking up a%s existing attribute named %s with value %s for selector %s in \n\t%s",
                    c.expectedTrue,
                    c.exists ? "n" : " not",
                    "'" + c.attributeName + "'",
                    c.attributeValue == null ? null : "'" + c.attributeValue + "'",
                    c.selector,
                    c.markup);
            System.out.println(message);
            if (c.expectedTrue) {
                Assert.assertTrue(message, HTMLExtractor.hasAttribute(c.url, c.markup, c.selector, c.exists, c.attributeName, c.attributeValue));
            } else {
                Assert.assertFalse(message, HTMLExtractor.hasAttribute(c.url, c.markup, c.selector, c.exists, c.attributeName, c.attributeValue));
            }

        }
    }
}
