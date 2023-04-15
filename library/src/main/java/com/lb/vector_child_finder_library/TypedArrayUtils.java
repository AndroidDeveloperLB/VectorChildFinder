package com.lb.vector_child_finder_library;

import android.content.res.TypedArray;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by ${Deven} on 1/31/18.
 */

public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static boolean hasAttribute(@NonNull final XmlPullParser parser, @NonNull final String attrName) {
        return parser.getAttributeValue(NAMESPACE, attrName) != null;
    }

    public static float getNamedFloat(@NonNull final TypedArray a, @NonNull final XmlPullParser parser, @NonNull final String attrName,
                                      final int resId, final float defaultValue) {
        final boolean hasAttr = hasAttribute(parser, attrName);
        if (!hasAttr) return defaultValue;
        else return a.getFloat(resId, defaultValue);
    }

    public static boolean getNamedBoolean(@NonNull final TypedArray a, @NonNull final XmlPullParser parser, @NonNull final String attrName,
                                          final int resId, final boolean defaultValue) {
        final boolean hasAttr = hasAttribute(parser, attrName);
        if (!hasAttr) return defaultValue;
        else return a.getBoolean(resId, defaultValue);
    }

    public static int getNamedInt(@NonNull final TypedArray a, @NonNull final XmlPullParser parser, @NonNull final String attrName,
                                  final int resId, final int defaultValue) {
        final boolean hasAttr = hasAttribute(parser, attrName);
        if (!hasAttr) return defaultValue;
        else return a.getInt(resId, defaultValue);
    }

    public static int getNamedColor(@NonNull final TypedArray a, @NonNull final XmlPullParser parser, @NonNull final String attrName,
                                    final int resId, final int defaultValue) {
        final boolean hasAttr = hasAttribute(parser, attrName);
        if (!hasAttr) return defaultValue;
        else return a.getColor(resId, defaultValue);
    }
}
