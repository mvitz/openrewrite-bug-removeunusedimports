# OpenRewrite Bug: Remove unused imports

The OpenRewrite recipe `org.openrewrite.java.RemoveUnusedImports` removes some imports although these are needed.

```
$ mvn rewrite:run
...
[INFO] <<< rewrite:5.39.2:run (default-cli) < process-test-classes @ openrewrite-bug-removeunusedimports <<<
[INFO]
[INFO]
[INFO] --- rewrite:5.39.2:run (default-cli) @ openrewrite-bug-removeunusedimports ---
[INFO] Using active recipe(s) [org.openrewrite.java.RemoveUnusedImports]
[INFO] Using active styles(s) []
[INFO] Validating active recipes...
[INFO] Project [openrewrite-bug-removeunusedimports] Resolving Poms...
[INFO] Project [openrewrite-bug-removeunusedimports] Parsing source files
[INFO] Running recipe(s)...
[INFO] Printing available datatables to: target/rewrite/datatables/2024-09-05_09-37-00-246
[WARNING] Changes have been made to src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java by:
[WARNING]     org.openrewrite.java.RemoveUnusedImports
[WARNING] Please review and commit the results.
[WARNING] Estimate time saved: 5m
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
...

$ git --no-pager diff
diff --git src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java
index 3a2da30..b861e85 100644
--- src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java
+++ src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java
@@ -5,7 +5,6 @@ import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.boot.context.properties.EnableConfigurationProperties;
-import org.springframework.boot.context.properties.bind.DefaultValue;
 import org.springframework.context.annotation.Bean;

 @SpringBootApplication

$ mvn compile
...
[INFO] --- compiler:3.13.0:compile (default-compile) @ openrewrite-bug-removeunusedimports ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 1 source file with javac [debug parameters release 21] to target/classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /Users/mvitz/Development/tmp/openrewrite-bug-removeunusedimports/src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java:[25,14] cannot find symbol
  symbol:   class DefaultValue
  location: class de.mvitz.openrewrite.bug.removeunusedimports.Application.TestProperties
[INFO] 1 error
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
...
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project openrewrite-bug-removeunusedimports: Compilation failure
[ERROR] /Users/mvitz/Development/tmp/openrewrite-bug-removeunusedimports/src/main/java/de/mvitz/openrewrite/bug/removeunusedimports/Application.java:[25,14] cannot find symbol
[ERROR]   symbol:   class DefaultValue
[ERROR]   location: class de.mvitz.openrewrite.bug.removeunusedimports.Application.TestProperties
[ERROR]
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```
