/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
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
package org.spoutcraft.mod.gui.builtin;

import org.spoutcraft.api.gui.Gui;
import org.spoutcraft.api.gui.component.Button;
import org.spoutcraft.api.gui.component.CheckBox;
import org.spoutcraft.api.gui.component.Frame;
import org.spoutcraft.api.gui.component.Label;
import org.spoutcraft.api.gui.component.RadioButton;
import org.spoutcraft.api.gui.component.RadioGroup;
import org.spoutcraft.api.gui.component.TextField;
import org.spoutcraft.api.gui.event.ActionEvent;
import org.spoutcraft.api.gui.event.EventHandler;
import org.spoutcraft.api.util.Color;

public class SpoutcraftTestGui extends Gui {
    private Button testBtn = new Button("Test Button");
    private TextField testField = new TextField();
    private CheckBox testBox = new CheckBox();
    private RadioGroup testGroup = new RadioGroup();
    private RadioButton testRadio1 = new RadioButton(testGroup);
    private RadioButton testRadio2 = new RadioButton(testGroup);
    private RadioButton testRadio3 = new RadioButton(testGroup);
    private int click = 0;
    private String[] messages = {
            "Test Button",
            "No for real test",
            "You keep clicking",
            "Why?",
            "Oh well",
            "You'll get tired eventually",
            "I think?",
            "Guess not...",
            "What is the end goal?",
            "You know there is an end...",
            "But it's not here yet!",
            "Or is it?",
            "Ok you win",
            "You've seen them all",
            "No really",
            "Nothing else to see!",
            "I'm done here."
    };

    public SpoutcraftTestGui() {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.getRoot().setBackground(new Color(0F, 0F, 0F, 0.4F));
        Label testLbl = new Label("Test Label");
        testLbl.setX(width / 2 - testLbl.getWidth() / 2);
        testLbl.setY(height / 2 - 85);
        add(testLbl);

        testBtn.setWidth(150);
        testBtn.setX(198 / 2 - 150 / 2);
        testBtn.setY(15);
        //testBtn.setX(width / 2 - 75);
        //testBtn.setY(height / 2 - testBtn.getHeight() - 50);
        //add(testBtn);

        testField.setWidth(150);
        testField.setX(198 / 2 - 150 / 2);
        testField.setY(50);

        testBox.setWidth(16);
        testBox.setHeight(16);
        testBox.setX(198 / 2 - 8);
        testBox.setY(85);

        int radioX = 198 / 2 - 32;
        int radioY = 85 - 18;
        RadioButton[] btns = new RadioButton[] {testRadio1, testRadio2, testRadio3};
        for (RadioButton btn : btns) {
            btn.setX(radioX);
            btn.setY(radioY);
            btn.setWidth(16);
            btn.setHeight(16);
            radioY += 18;
        }

        Frame testFrm = new Frame();
        testFrm.setWidth(200);
        testFrm.setHeight(150);
        testFrm.setX(width / 2 - 100);
        testFrm.setY(height / 2 - 70);
        testFrm.addComponent(testBtn);
        testFrm.addComponent(testField);
        testFrm.addComponent(testBox);
        for (RadioButton btn : btns) {
            testFrm.addComponent(btn);
        }

        add(testFrm);

        testBtn.addEventListeners(this);
    }

    @EventHandler
    public void onClick(ActionEvent evt) {
        click = (click + 1) % messages.length;
        testBtn.setText(messages[click]);
    }
}
