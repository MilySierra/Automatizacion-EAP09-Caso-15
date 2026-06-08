Feature: I as a provider, want to define the business hours to can add mine services availability

    Background:
        Given I am an authenticated provider at the general business hours page

    Scenario Outline: successful business hours definition
        When I define a valid set of business hours:
            | dayOfWeek   | <dayOfWeek>   |
            | openingTime | <openingTime> |
            | closingTime | <closingTime> | 

        Then I can see a message of correct definition
    
        Examples:
            | dayOfWeek | openingTime | closingTime |
            #| LUNES     | 10:00AM    | 06:00PM    |
            | MARTES    | 09:00AM     | 05:00PM    |
            #| SABADO    | 10:00AM    | 05:00PM    |
    
    Scenario Outline: successful update of business hours
        When I update the set of business hours for one day:
            | dayOfWeek   | <dayOfWeek>   |
            | openingTime | <openingTime> |
            | closingTime | <closingTime> | 

        Then I can see a message of correct update
        
        Examples:
        | dayOfWeek | openingTime | closingTime |
        #| LUNES     | 09:00AM    | 04:00PM     |
        #| MARTES    | 09:00AM    | 05:00PM     |
        | SABADO    | 10:00AM     | 04:00PM     |
    
    Scenario Outline: Failed to define business hours due to opening time after closing time    
        When I define the opening time after the closing time:
            | dayOfWeek   | <dayOfWeek>   |
            | openingTime | <openingTime> |
            | closingTime | <closingTime> | 
        Then I can see an error message related with incompatible hours

        Examples:
        | dayOfWeek | openingTime | closingTime |
        #| LUNES     | 04:00PM    | 10:00AM    |   
        #| MARTES    | 05:00PM    | 01:00PM    |
        | SABADO    | 10:00AM    | 09:00AM     | 