package top

/*
  This lambda will detect updates to the list of profane words maintained in S3. When there is an update,
  it will retrieve the list of profane names and use these to re-generate the profanity filter regex.
  This new regex is then uploaded (in string form) into a separate bucket.
 */

/*
  Development steps:
    1 - Detect an update to the object containing the list of profane words.
    2 - Retrieve the list of profane words from S3.
    3 - Re-generate the regex.
    4 - Upload the regex to S3.
 */

class RegexBuilder {

}
