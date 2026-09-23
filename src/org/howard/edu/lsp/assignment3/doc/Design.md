# Assignment 3 Design.md
1.) How was my Assignment 2 solution organized?
I used a single class with the methods “main” and “transform”. The “transform” method was a private helper that handled parsing, validation, payroll calculations, formatting, etc. All logic was in one file and the row data was given using strings

2.) What design changes did I make for Assignment 3? What classes or abstractions did I introduce?
I split the single class from Assignment 2 into five different classes that all had their own purpose. Employee, EmployeeParser, EmployeeTransformer, CsvWriter, and ETLPipeline.

3.)How did I divide responsibilities differently?
EmployeeParser handled row parsing and validation. Employee handled payroll math, rouding, pay-level, and status. EmployeeTransformer handled csv output formatting, and ETLPipeline handled orchestration.

4. Why do I believe my assignment 3 is an improvement?
I believe it’s an improvement because now the program’s different roles are designated in a more organized fashion, so if I needed to review or let someone else work on my code, it would be a lot easier to manipulate specific aspects without having to make sure every other aspect is left unaffected.

5. AI and Internet resources.
For my AI and internet resources, because I am less familiar with Java, I have been using a plethora of different inquiries. I have mainly used google to teach me different commands and the basics of JavaScript. Since the program isn’t as hard conceptually, my biggest roadblock is being more familiar with the python language so translation was the biggest need. I have been able to do so with the help of Google, YouTube and any other outside help I’ve been able to find from peers as well.
https://www.google.com/search?q=google&oq=google&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIHCAEQABiPAjIHCAIQABiPAjIGCAMQRRg80gEHNjUyajBqN6gCALACAA&sourceid=chrome&source=chrome.ob&ie=UTF-8&sei=i0G0atabE63U5NoP79KI-Ak
