package com.romanov.labs.lab4;

import android.content.Context;
import android.net.Uri;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlHelper {
    private final static String fileName = "cards.xml";

    public static void saveToXml(Context context, List<Card> cards) {
        XmlSerializer serializer = Xml.newSerializer();
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            serializer.setOutput((OutputStream) fos, "UTF-8");
            serializer.startDocument(null, Boolean.TRUE);
            serializer.startTag(null, "cards");

            for (Card card : cards) {
                saveCard(serializer, card);
            }

            serializer.endTag(null, "cards");
            serializer.endDocument();
            serializer.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveCard(XmlSerializer serializer, Card card) throws IOException {
        serializer.startTag(null, "card");

        serializer.startTag(null, "question");
        serializer.text(card.getQuestion());
        serializer.endTag(null, "question");

        serializer.startTag(null, "example");
        serializer.text(card.getExample());
        serializer.endTag(null, "example");

        serializer.startTag(null, "answer");
        serializer.text(card.getAnswer());
        serializer.endTag(null, "answer");

        serializer.startTag(null, "translate");
        serializer.text(card.getTranslate());
        serializer.endTag(null, "translate");

        if (card.getImageURI() != null) {
            serializer.startTag(null, "imageUri");
            serializer.text(card.getImageURI());
            serializer.endTag(null, "imageUri");
        }

        serializer.endTag(null, "card");
    }
    public static List<Card> readFromXml(Context context, String fileName) {
        List<Card> cards = new ArrayList<>();
        FileInputStream fis = null;

        try {
            fis = context.openFileInput(fileName);
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(fis, null);
            parser.nextTag();

            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }

                String tagName = parser.getName();

                if (tagName.equals("card")) {
                    Card card = readCard(parser);
                    cards.add(card);
                }
            }
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cards;
    }

    private static Card readCard(XmlPullParser parser) throws IOException, XmlPullParserException {
        String question = null;
        String example = null;
        String answer = null;
        String translate = null;
        String imageUriString = null;

        parser.require(XmlPullParser.START_TAG, null, "card");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            switch (name) {
                case "question" -> question = readText(parser);
                case "example" -> example = readText(parser);
                case "answer" -> answer = readText(parser);
                case "translate" -> translate = readText(parser);
                case "imageUri" -> imageUriString = readText(parser);
                default -> skip(parser);
            }
        }

        parser.require(XmlPullParser.END_TAG, null, "card");

        return new Card(0, question, example, answer, translate, Uri.parse(imageUriString).toString());
    }

    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG -> depth--;
                case XmlPullParser.START_TAG -> depth++;
            }
        }
    }
}
