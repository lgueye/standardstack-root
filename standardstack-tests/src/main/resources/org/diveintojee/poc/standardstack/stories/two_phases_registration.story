Meta:

Narrative:
As a system
I want to provide 2-phase registration
So that I can limit toxic registrations

Scenario: draft account should be persisted properly
Given visitor <email> intends to register with the following information:
|first_name|last_name|
|awesome   |visitor  |
When visitor <email> registers
Then visitor <email> registration is properly persisted
When visitor <email> confirms registration
Then visitor <email> account is active

Examples:
|email	            |
|awesome@visitor.com|
