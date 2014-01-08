/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spoutcraft.api.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ReflectionUtil {
    private ReflectionUtil() {}

    public static void setFinalField(Object instance, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
        if (instance == null) {
            throw new IllegalArgumentException("Instance cannot be null!");
        }
        setFinalField(instance, instance.getClass().getDeclaredField(fieldName), value);
    }

    public static void setFinalField(Class<?> clazz, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
        setFinalField(null, clazz.getDeclaredField(fieldName), value);
    }

    public static void setFinalField(Object instance, Field field, Object value) throws IllegalArgumentException, IllegalAccessException, SecurityException {
        field.setAccessible(true);

        Field modifiersField;
        try {
            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        } catch (NoSuchFieldException ignored) {
        }

        field.set(instance, value);
    }
}