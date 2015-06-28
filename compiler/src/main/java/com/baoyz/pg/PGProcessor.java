/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 baoyongzhang <baoyz94@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.baoyz.pg;

import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * ParcelableGenerator
 * Created by baoyz on 15/6/24.
 */

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.baoyz.pg.Parcelable")
public class PGProcessor extends AbstractProcessor{

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);

        filer = env.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        try {
            Set<? extends Element> set = roundEnv
                    .getElementsAnnotatedWith(Parcelable.class);
            for (Element element : set) {
                try {
                    TypeElement enclosingElement = (TypeElement) element;
                    ProxyInfo pi = new ProxyInfo(enclosingElement
                            .getQualifiedName().toString());
                    writeLog(pi.getFullName());
                    JavaFileObject jfo = filer.createSourceFile(
                            pi.getFullName(), enclosingElement);
                    Writer writer = jfo.openWriter();
                    writeLog(pi.createCode());
                    writer.write(pi.createCode());
                    writer.flush();
                    writer.close();
                    writeLog("ok");
                } catch (Exception e) {
                    e.printStackTrace();
                    writeLog(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeLog(e.getMessage());
        }
        return true;
    }

    private void writeLog(String str) {
        // try {
        // FileWriter fw = new FileWriter(new File("D:/process.txt"), true);
        // fw.write(str + "\n");
        // fw.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
}
