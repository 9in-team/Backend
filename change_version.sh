NEW_VERSION=$1

YAML_CONTENT=`awk '/image:/' 9in-mono.yml`
CURRENT_VERSION="${YAML_CONTENT#*9in-mono:}"

sed -i "s/mono:${CURRENT_VERSION}/mono:${NEW_VERSION}/g" 9in-mono.yml