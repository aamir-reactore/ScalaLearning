/**
  * Check the String contains
  * only four specific characters
  */

"!123".matches("[a-zA-Z0-9]{4}")

"!1  ".matches("[a-zA-Z0-9]{4}")

"ab12".matches("[a-zA-Z0-9]{4}")

"34Az".matches("[a-zA-Z0-9]{4}")

"123".matches("[a-zA-Z0-9]{4}")

/**
  *Extract parts of a string
  */