package com.baoyz.pg;

import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ÉÏÎç1:34:35
 */
public class PGProcessor extends AbstractProcessor {

	private Filer filer;

	@Override
	public synchronized void init(ProcessingEnvironment env) {
		super.init(env);

		filer = env.getFiler();
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> supportTypes = new LinkedHashSet<String>();
		supportTypes.add(Parcelable.class.getCanonicalName());

		return supportTypes;
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
