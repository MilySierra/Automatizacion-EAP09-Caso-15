Feature: I as a provider user, want to set up different time slots for my services

    # Creates a new provider and add an standard 9AM-5PM business hours monday-saturday
    # and 10AM-5PM sundays and adds some service of 45 minutes duration
    Background:
        Given I am registered provider with at least one registered service with general business hours defined
        And I am an authenticated at the service availability management page
        
    Scenario Outline: successful definition of availability
        # hours wittin the business hours and slots of one hour duration
        When I set up a valid time slot availability for my service:
            | startTime     | <startTime>    |
            | endTime       | <endTime>      |
        Then I can see a message of correct definition of availability

        Examples:
            |  startTime | endTime |
            |  10:00AM   | 11:00AM |
            #|  3:00PM    | 4:00PM  |
            #|  4:00PM    | 5:00PM  |

    Scenario Outline: failed set up due to time slot outside the defined business hours
        When I try to define a time slot outside the business hours:
            | startTime     | <startTime>    |
            | endTime       | <endTime>      |
        Then I see an error message of time slot outside the business hours

        Examples:
            | startTime | endTime |
            #|  08:00AM  | 09:00AM |
            #|  05:00PM  | 06:00PM |
            |  04:30PM  | 05:30pm |
    
    Scenario Outline: failed to set up due to time slot overlap with existin availability
        And I already have a time slot defined for the service:
            | existingStartTime | <existingStartTime> |
            | existingEndTime   | <existingEndTime>   |

        When I try to define an overlapping time slot:
            | startTime | <startTime> |
            | endTime   | <endTime>   |

        Then I can see an overlapping error message

        Examples: 
            | existingStartTime | existingEndTime | startTime | endTime |
            |       11:00AM     |     12:00PM     |  11:30AM  | 12:30PM |
            #|       01:00PM     |     02:00PM     |  01:00PM  | 02:00PM |
            #|       03:00PM     |     04:00PM     |  03:15PM  | 04:20PM |
