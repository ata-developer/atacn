# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# Install Datasource dependencies and configure datasource in Glassfish
FROM        amazon/aws-eb-glassfish:5.0-al-onbuild-2.11.1
# Exposes port 8080
EXPOSE      8080

RUN         curl -L -o /usr/local/glassfish5/glassfish/domains/domain1/lib/postgresql-42.2.6.jar https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.6/postgresql-42.2.6.jar && \
            asadmin start-domain domain1