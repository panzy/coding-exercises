# 420. Strong Password Checker

Hard

A password is considered strong if the below conditions are all met:

It has at least 6 characters and at most 20 characters.
It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
It does not contain three repeating characters in a row (i.e., "...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Given a string password, return the minimum number of steps required to make password strong. if password is already strong, return 0.

In one step, you can:

Insert one character to password,
Delete one character from password, or
Replace one character of password with another character.

Constraints:

- 1 <= password.length <= 50
- password consists of letters, digits, dot '.' or exclamation mark '!'.

## Notes

This problem made me struggling a whole day.

It would save me hours if I had realized that since the password.length
is restricted very low (50), this problem is harder than it looks,
don't assume it can be easily solved linearly.
