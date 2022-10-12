Quick instructions:

setopt HIST_IGNORE_SPACE
export ORG_GRADLE_PROJECT_signingKey=$(gpg --export-secret-keys --armor KEYID_HERE)
 export ORG_GRADLE_PROJECT_signingPassword='PASSWORD HERE'
export OSSRH_USERNAME=USERNAME HERE
 export OSSRH_PASSWORD='PASSWORD HERE'

./gradlew clean build publishFabriktPublicationToOSSRHRepository
