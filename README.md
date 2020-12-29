# Attention! 
This is not a fully completed project. It is being developed all the time. The current version is only demonstration and does not represent the entire project but a fraction of it.

# Description

A web application that allows you to register patients, check doctors, order tests and add results. It is possible to add test setups, doctors, workstations.

Additionally, the ability to create doctor boards and order cards on **Trello** has been added.
**Api Medic** was used to obtain symptoms and obtain a probable diagnosis based on them/

Web application that allows patients to register for a medical visit. Additionally, it enables entering the results, managing patient data, sending e-mails with notifications to doctors.

- Main: Java, Spring, SpringBoot, Hibernate
- Additional: SQL, Vaadin for design [basic form]
- For testing: JUnit4, Mockito, [Selenium]
- App builder: Gradle
- Version control: Git
- DataBases: MySQL, [PostgreSQL for Heroku]
- Patterns: builder for most of components, [observer for doctors - mail when test is resulted], singleton for repositories, [decorator for bill creation]
- Additionally: lombok, [thymeleaf]
- External Api: Trello - information about orders, new board for all doctors, ApiMedic - symptoms and diagnosis for visits

[In square brackets I mark elements not yet implemented]

# Microservices 

The project will be divided and enriched into microservices responsible for individual api. Currently, the base / configuration microservices are available in the link below.
`<GitHub>` : <https://github.com/zagPatryk/Microservices>

# ApiMedic

"ApiMedic offers a medical symptom checker primarily for patients. Based on the entered symptoms it tells the patient what possible diseases he has. It directs him to more medical information and shows the right doctor for further clarifications. The symptom checker can be integrated via the flexible API (Application Programming Interface). This is a modular programming interface, which offers the symptom checker functionalities for a main program."
`<ApiMedic>` : <https://apimedic.com/>

# TrelloApi

"Trello’s boards, lists, and cards enable teams to organize and prioritize projects in a fun, flexible, and rewarding way."
`<TrelloApi>` : <https://developer.atlassian.com/cloud/trello/rest/api-group-actions/>


# Endpoints
![E1](https://github.com/zagPatryk/DrMed/blob/master/info/EndPoints1.png)
![E2](https://github.com/zagPatryk/DrMed/blob/master/info/EndPoints2.png)
![E3](https://github.com/zagPatryk/DrMed/blob/master/info/EndPoints3.png)

# Frontend

This is what the target application plan looks like:

![S](https://github.com/zagPatryk/DrMed/blob/master/info/S.png)

You can currently add a new doctor or view all of them. After selecting a doctor from the list and pressing 'Go to' you can edit his details.

![F1](https://github.com/zagPatryk/DrMed/blob/master/info/F1.png)
![F2](https://github.com/zagPatryk/DrMed/blob/master/info/F2.png)
![F3](https://github.com/zagPatryk/DrMed/blob/master/info/F3.png)

# Base

Base use:

![B](https://github.com/zagPatryk/DrMed/blob/master/info/B.png)

# Tests

This will be extended to all backend and frontend functionalities. Current tests cover:

![T](https://github.com/zagPatryk/DrMed/blob/master/info/T.png)
