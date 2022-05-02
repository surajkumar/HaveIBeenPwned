# HaveIBeenPwned

## Introduction
[HaveIBeenPwned](https://haveibeenpwned.com/) is a website that allows users to check whether their personal information has been compromised by data breeches. The service collects and analyzes hundreds of database dumps and pastes containing information about billions of leaked accounts, and allows users to search for their own information by entering their username or email address. Additionally, passwords may also be checked to see if they are within the public breech list.

This repoisitory contains code that leverages the public HaveIBeenPwned API so that you can check usernames and passwords to see if they exist within the HaveIBeenPwned database. 

## Usage:

```
boolean isUsernamePwned = HaveIBeenPwned.accountPwned("username");
boolean isPasswordPwned = HaveIBeenPwned.pwned("password");
```

### System requirements
* Java 7 or higher

## Why is this important?
It is our duty as business owners and developers to ensure that users using our services are protected from potential security risks. A common mistake people use is re-using the same password for multiple accounts. By using this ulility, you can atleast enforce users to specify passwords that are not in a public database (to lower the risk of a hack) and or warn users that the details the are using may pose a security to risk to themselves and their data.

## Contributing
Contributions are welcome whether it is for small bug fixes or new pieces of major functionality. To contribute changes, you should first fork the upstream repository to your own GitHub account. You can then add a new remote for `upstream` and rebase any changes you make and keep up-to-date with the upstream.

`git remote add upstream https://github.com/surajkumar/PBKDF2WithHmacSHA512.git`
