(function ($) {
    $.fn.jquizzy = function (settings) {
        var defaults = {
            questions: null,
            startImg: 'Exam/images/start.gif',
            endText: '请认真检查试卷，确认无误后提交!',
            shortURL: null,
            sendResultsURL: 'http://localhost:8080/Exam_submit_success.jsp',
            resultComments: {
                perfect: 'perfect?',
                excellent: 'excellent!',
                good: 'good!',
                average: 'average。',
                bad: 'bad',
                poor: 'poor！',
                worst: 'worst！'
            }
        };
        var config = $.extend(defaults, settings);
        if (config.questions === null) {
            $(this).html('<div class="intro-container slide-container"><h2 class="qTitle">添加问题失败</h2></div>');
            //添加问题失败
            return;
        }
        var superContainer = $(this),
            answers = [], //存储答案
            introFob = '<div class="intro-container slide-container">请认真完成测试题，剩余考试时间显示在左上方<br/><br/></div>',
            //开始介绍
            exitFob = '<div class="results-container slide-container"><div class="question-number">' + config.endText + '</div>' +
                '<div class="result-keeper"></div>' +
                '</div><div class="notice">您还有试题未答！</div>' +
                '<div class="progress-keeper" ><div class="progress"></div></div>',
            contentFob = '',
            questionsIteratorIndex, //问题
            answersIteratorIndex;   //答案
        superContainer.addClass('main-quiz-holder');
        for (questionsIteratorIndex = 0; questionsIteratorIndex < config.questions.length; questionsIteratorIndex++) {
            contentFob += '<div class="slide-container"><div class="question-number">' + (questionsIteratorIndex + 1) + '/' + config.questions.length + '</div><div class="question">' + config.questions[questionsIteratorIndex].question + '</div><ul class="answers">';
            for (answersIteratorIndex = 0; answersIteratorIndex < config.questions[questionsIteratorIndex].answers.length; answersIteratorIndex++) {
                contentFob += '<li>' + config.questions[questionsIteratorIndex].answers[answersIteratorIndex] + '</li>';
            }
            contentFob += '</ul><div class="nav-container">';
            // if (questionsIteratorIndex !== 0) {
            //     contentFob += '<div class="prev"><a class="nav-previous" href="#">&lt; 上一题</a></div>';
            //     //添加上一题标签
            // }
            // if (questionsIteratorIndex < config.questions.length - 1) {
            //     contentFob += '<div class="next"><a class="nav-next" href="#">下一题 &gt;</a></div>';
            //     //添加下一题标签
            // } else {

            //     //答题完成标签
            // }
            contentFob += '</div></div>';
            //将json数组中正确问题的答案存入answers数组
            answers.push(config.questions[questionsIteratorIndex].correctAnswer);
        }
        contentFob += '<div class="slide-container final"><a class="nav-show-result" href="Exam_submit_success.jsp" target="_blank">提交试卷</a></div>';
        contentFob += '<div class="success"><a class="nav-show-result" href="Stu_sys.jsp" target="_blank">返回个人管理界面</a></div>';
        //向html中添加刚刚生成的代码
        superContainer.html(introFob + contentFob + exitFob);

        var progress = superContainer.find('.progress'),
            progressKeeper = superContainer.find('.progress-keeper'),
            notice = superContainer.find('.notice'),
            progressWidth = progressKeeper.width(),

            userAnswers = [],
            questionLength = config.questions.length,
            slidesList = superContainer.find('.slide-container');
        var suc = superContainer.find('.success');
        suc.hide();
        //检查答案
        function checkAnswers() {
            var resultArr = [],
                flag = false;
            for (i = 0; i < answers.length; i++) {
                if (answers[i] == userAnswers[i]) {
                    flag = true;
                } else {
                    flag = false;
                }
                resultArr.push(flag);
            }
            return resultArr;
        }

        // function roundReloaded(num, dec) {
        //     var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
        //     return result;
        // }
        // function judgeSkills(score) {
        //     var returnString;
        //     if (score === 100) return config.resultComments.perfect;
        //     else if (score > 90) return config.resultComments.excellent;
        //     else if (score > 70) return config.resultComments.good;
        //     else if (score > 50) return config.resultComments.average;
        //     else if (score > 35) return config.resultComments.bad;
        //     else if (score > 20) return config.resultComments.poor;
        //     else return config.resultComments.worst;
        // }
        progressKeeper.hide();
        notice.hide();
        // slidesList.hide().first().fadeIn(500);
        superContainer.find('li').click(function () {
            var thisLi = $(this);
            if (thisLi.hasClass('selected')) {
                thisLi.removeClass('selected');
            } else {
                thisLi.parents('.answers').children('li').removeClass('selected');
                thisLi.addClass('selected');
            }
        });
        // superContainer.find('.nav-start').click(function() {
        //     $(this).parents('.slide-container').fadeOut(500,
        //     function() {
        //         $(this).next().fadeIn(500);
        //         progressKeeper.fadeIn(500);
        //     });
        //     return false;
        // });
        // superContainer.find('.next').click(function() {
        //     if ($(this).parents('.slide-container').find('li.selected').length === 0) {
        //         notice.fadeIn(300);
        //         return false;
        //     }
        //     notice.hide();
        //     $(this).parents('.slide-container').fadeOut(500,
        //     function() {
        //         $(this).next().fadeIn(500);
        //     });
        //     progress.animate({
        //         width: progress.width() + Math.round(progressWidth / questionLength)
        //     },
        //     500);
        //     return false;
        // });
        // superContainer.find('.prev').click(function() {
        //     notice.hide();
        //     $(this).parents('.slide-container').fadeOut(500,
        //     function() {
        //         $(this).prev().fadeIn(500);
        //     });
        //     progress.animate({
        //         width: progress.width() - Math.round(progressWidth / questionLength)
        //     },
        //     500);
        //     return false;
        // });
        superContainer.find('.final').click(function () {
            // if ($(this).parents('.slide-container').find('li.selected').length === 0) {
            //     notice.fadeIn(300);
            //     return false;
            // }
            superContainer.find('li.selected').each(function (index) {
                userAnswers.push($(this).parents('.answers').children('li').index($(this).parents('.answers').find('li.selected')) + 1);
            });
            if (config.sendResultsURL !== null) {

                var collate = [];
                for (r = 0; r < userAnswers.length; r++) {
                    collate.push('{"questionNumber":"' + parseInt(r + 1, 10) + '", "userAnswer":"' + userAnswers[r] + '"}');
                }
                slidesList.hide();
                suc.show();
                $.ajax({
                    type: 'POST',
                    url: config.sendResultsURL,
                    data: '{"answers": [' + collate.join(",") + ']}',
                    complete: function () {
                        console.log("OH HAI");
                    }
                });
            }
            // progressKeeper.hide();
            // var results = checkAnswers(),
            // resultSet = '',
            // trueCount = 0,
            // shareButton = '',
            // score,
            // url;
            // if (config.shortURL === null) {
            //     config.shortURL = window.location
            // };
            // for (var i = 0,
            // toLoopTill = results.length; i < toLoopTill; i++) {
            //     if (results[i] === true) {
            //         trueCount++;
            //         isCorrect = true;
            //     }
            //     resultSet += '<div class="result-row">' + (results[i] === true ? "<div class='correct'>#"+(i + 1)+"<span></span></div>": "<div class='wrong'>#"+(i + 1)+"<span></span></div>");
            //     resultSet += '<div class="resultsview-qhover">' + config.questions[i].question;
            //     resultSet += "<ul>";
            //     for (answersIteratorIndex = 0; answersIteratorIndex < config.questions[i].answers.length; answersIteratorIndex++) {
            //         var classestoAdd = '';
            //         if (config.questions[i].correctAnswer == answersIteratorIndex + 1) {
            //             classestoAdd += 'right';
            //         }
            //         if (userAnswers[i] == answersIteratorIndex + 1) {
            //             classestoAdd += ' selected';
            //         }
            //         resultSet += '<li class="' + classestoAdd + '">' + config.questions[i].answers[answersIteratorIndex] + '</li>';
            //     }
            //     resultSet += '</ul></div></div>';
            // }
            // score = roundReloaded(trueCount / questionLength * 100, 2);
            //
            // resultSet = '<h2 class="qTitle">' + judgeSkills(score) + '<br/> 您的分数： ' + score + '</h2>' + shareButton + '<div class="jquizzy-clear"></div>' + resultSet + '<div class="jquizzy-clear"></div>';
            // superContainer.find('.result-keeper').html(resultSet).show(500);
            // superContainer.find('.resultsview-qhover').hide();
            // superContainer.find('.result-row').hover(function() {
            //     $(this).find('.resultsview-qhover').show();
            // },
            // function() {
            //     $(this).find('.resultsview-qhover').hide();
            // });
            // $(this).parents('.slide-container').fadeOut(500,
            // function() {
            //     $(this).next().fadeIn(500);
            // });
            return false;
        });
    };
})(jQuery);