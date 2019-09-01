MD "mods\"
DEL "mods\homestead-*.jar"
XCOPY "..\build\libs\homestead-*.jar" "mods\"
java -Xmx2G -Xms1G -jar "spongevanilla-1.12.2-7.1.6.jar"
PAUSE
