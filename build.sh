#/bin/bash
javac -cp lib/commons-codec-1.7.jar:lib/commons-collections-3.2.1.jar:lib/commons-fileupload-1.2.1.jar:lib/commons-io-1.3.2.jar:lib/commons-lang-2.6.jar:lib/commons-logging-1.2.jar:lib/httpclient-4.5.3.jar:lib/httpcore-4.4.6.jar:lib/json-simple-1.1.1.jar \
com/ieee/smartcard/*.java

jar -cfm smartcard.jar Manifest.text com/ieee/smartcard/*.class
