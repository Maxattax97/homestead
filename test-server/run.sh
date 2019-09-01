#! /bin/bash
VERSION=1.12.2-7.1.6
URL="https://repo.spongepowered.org/maven/org/spongepowered/spongevanilla/${VERSION}/spongevanilla-${VERSION}.jar"

if [[ ! -s "spongevanilla-${VERSION}.jar" ]]; then
    rm -f minecraft_server.*.jar
    rm -f spongevanilla-*.jar
    wget "$URL"
fi

if [[ ! -s "eula.txt" ]]; then
    echo "eula=true" > eula.txt
fi

rm -f mods/homestead-*.jar
cp ../build/libs/homestead-*.jar mods/

java -Xmx2G -Xms1G -jar "spongevanilla-${VERSION}.jar"
