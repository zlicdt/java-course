mkdir -p build
javac -cp .:lib/sqlite-jdbc-3.42.0.0.jar -d build main/Main.java db/*.java exceptions/*.java gui/*.java
echo "Main-Class: main.Main" > build/MANIFEST.MF
echo "Class-Path: lib/sqlite-jdbc-3.42.0.0.jar" >> build/MANIFEST.MF
mkdir -p build/lib
cp lib/sqlite-jdbc-3.42.0.0.jar build/lib/
cd build
jar cfm ClassroomBookingSystem.jar MANIFEST.MF main/*.class db/*.class exceptions/*.class gui/*.class
mv ClassroomBookingSystem.jar ..
cd ..
rm -rf build
echo "Done! JAR file created: ClassroomBookingSystem.jar"
echo "To run the application: java -jar ClassroomBookingSystem.jar"
chmod +x ClassroomBookingSystem.jar