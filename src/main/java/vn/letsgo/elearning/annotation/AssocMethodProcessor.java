package vn.letsgo.elearning.annotation;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("vn.letsgo.elearning.annotation.AssocMethod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class AssocMethodProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment env){ }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(AssocMethod.class)) {
            if (element.getKind().isField()) {
                generateMethod((TypeElement) element.getEnclosingElement(), element);
            }
        }
        return true;
    }

    private void generateMethod(TypeElement enclosingElement, Element fieldElement) {
        String methodName = "add" + fieldElement.getSimpleName().toString().substring(0, 1).toUpperCase() +
                fieldElement.getSimpleName().toString().substring(1);

        try {
            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                    enclosingElement.getQualifiedName() + "Generated");
            try (PrintWriter out = new PrintWriter(jfo.openWriter())) {
                out.println("package " + enclosingElement.getEnclosingElement() + ";");
                out.println();
                out.println("public class " +
                        enclosingElement.getSimpleName() + "Generated {");
                out.println();
                out.println("    public void " + methodName + "(" +
                        fieldElement.asType() + " " + fieldElement.getSimpleName() + ") {");
                out.println("        this." + fieldElement.getSimpleName() + ".add(" +
                        fieldElement.getSimpleName() + ");");
                out.println("        " + fieldElement.getSimpleName() +
                        ".set" + enclosingElement.getSimpleName() + "(this);");
                out.println("    }");
                out.println("}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
