/**
 * A CLI script to create solution folders for LeetCode problems.
 *
 * It uses LeetCode's graphql API to fetch problem details, like id, title and description.
 */

const fetch = require('node-fetch');
const fs = require('fs');

function main() {
  let m;
  if (process.argv.length !== 3 || !process.argv[2] || !(m = process.argv[2].match(/^https\:\/\/leetcode.com\/problems\/([^/]+)/))) {
    console.error(`Usage: node ${process.argv[1]} leetcode-problem-url\n`);
    return;
  }

  let questionUrl = process.argv[2]; // example: "https://leetcode.com/problems/ambiguous-coordinates";
  let titleSlug = m[1];

  let postData = {
    "operationName": "questionData",
    "variables": {
      "titleSlug": titleSlug,
    },
    "query": "query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    questionId\n    questionFrontendId\n    boundTopicId\n    title\n    titleSlug\n    content\n    translatedTitle\n    translatedContent\n    isPaidOnly\n    difficulty\n    likes\n    dislikes\n    isLiked\n    similarQuestions\n    exampleTestcases\n    contributors {\n      username\n      profileUrl\n      avatarUrl\n      __typename\n    }\n    topicTags {\n      name\n      slug\n      translatedName\n      __typename\n    }\n    companyTagStats\n    codeSnippets {\n      lang\n      langSlug\n      code\n      __typename\n    }\n    stats\n    hints\n    solution {\n      id\n      canSeeDetail\n      paidOnly\n      hasVideoSolution\n      paidOnlyVideo\n      __typename\n    }\n    status\n    sampleTestCase\n    metaData\n    judgerAvailable\n    judgeType\n    mysqlSchemas\n    enableRunCode\n    enableTestMode\n    enableDebugger\n    envInfo\n    libraryUrl\n    adminUrl\n    __typename\n  }\n}\n"
  };

  fetch('https://leetcode.com/graphql', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(postData),
  }).then(r => {
    if (r.ok) {
      return r.json().then(j => {
        const { questionFrontendId, title, titleSlug, content } = j.data.question;
        const folder = titleSlug.replace('-', '_') + '_' + questionFrontendId;

        // |content| is the description of the problem in HTML.
        // It would be nice to output it to a HTML version of readme file,
        // but for copyright reason, I'm not doing that.
        const readmeContent = `# ${questionFrontendId}. ${title}\n\n` + 
          `[${questionUrl}](${questionUrl})\n`;

        return fs.promises.mkdir(folder)
          .then(() => fs.promises.writeFile(folder + '/README.md', readmeContent))
          .then(() => fs.promises.writeFile(folder + '/Solution.cpp',
            '/**' + '\n' +
            ' * ' + questionFrontendId + '. ' + title +  '\n' +
            ' * ' + questionUrl + '\n' +
            ' * ' + '\n' +
            ' * --' + '\n' +
            ' * Created by Zhiyong \n' + 
            ' * ' + new Date().toLocaleString() + '\n' +
            ' */\n'))
          .then(() => console.log(folder + '/ created.\n'));
      });
    }
  }, err => {
    console.error(err.message);
  })
}

main();
