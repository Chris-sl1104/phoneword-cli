# PhoneWords Java CLI

A command-line Java application that converts phone numbers into possible word combinations (phonewords), similar to how companies use phonewords in advertising.

## Features
- Accepts input from a file or standard input (STDIN).
- Configurable dictionary using the `-dictionary` command-line argument.
- Outputs results in uppercase, separated by dashes, following classic phone keypad rules.

## Usage

### Compile

```bash
javac -d out src/com/example/phonewords/*.java
