This project needs:
Eclipse+ m2e plugin +  maven integration eclipse wtp

http://marketplace.eclipse.org/content/maven-integration-eclipse-wtp

This repo contains several projects, each with a maven descriptor file (pom.xml), so that they can be compiled. 
I tried to have bare-bones functionality in each one.

Descriptions:

template-basicWebApp: 
    A simple bare bones java web app. Encodes all output to utf-8, but nothing more.

template-springmvc-simple: 
    Configures Springmvc, spring context, and uses jsp and jstl. 
    Shows a simple form with validation,  and simple mvc mappings using spring MVC.
    (Does not use spring security)  


template-springmvc-jquery:
    Based in template-springmvc-simple, basically the same but with Ajax requests using jquery.
    Configures Springmvc, spring context, and uses jsp and jstl. 
    Shows a simple form with JSR 303 validation and AJax request using jqueryUI.
    (Does not use spring security)  
    

template-spring-primefaces:
    Incomplete project with only good wishes.


template-backend:
    Incomplete project with only good wishes.

