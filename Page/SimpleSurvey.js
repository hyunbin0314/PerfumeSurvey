import React, { useState } from 'react'
import '../CSS/simpletest.scss'
import axios from 'axios';
const SimpleSurvey = () => {

    const questions = [
        "아침에 상쾌한 느낌을 선호하십니까?",   // 시트러스
        "달콤하고 싱그러운 향을 좋아하십니까?",  // 프룻티
        "로맨틱하고 여성스러운 향을 좋아하십니까?",  // 플로럴
        "허브와 향신료의 자연스러운 향을 좋아하십니까?",  // 아로마틱
        "강렬하고 깊은 향을 선호하십니까?",  // 스모키
        "따뜻하고 자극적인 향을 좋아하십니까?",  // 스파이시
        "깨끗하고 순수한 향을 좋아하십니까?",  // 코튼
        "부드럽고 섹시한 향을 선호하십니까?",  // 화이트 머스크
        "바다의 상쾌함을 느끼고 싶으십니까?",  // 아쿠아틱
        "따뜻하고 매혹적인 향을 좋아하십니까?",  // 앰버
        "신선한 풀내음을 좋아하십니까?",  // 그린
        "신비롭고 영적인 향을 좋아하십니까?",  // 인센스
        "이국적이고 풍부한 향을 선호하십니까?",  // 오리엔탈
        "자연스럽고 흙내음을 좋아하십니까?",  // 얼디
        "상쾌하고 풋풋한 향을 좋아하십니까?",  // 허벌
        "부드럽고 고운 향을 좋아하십니까?",  // 파우더리
        "달콤하고 따뜻한 향을 좋아하십니까?",  // 바닐라
        "따뜻하고 목가적인 향을 좋아하십니까?"  // 우디
    ];


    const options = [
        { text: "매우 그렇지 않다", value: 2 },
        { text: "그렇지 않다", value: 4 },
        { text: "보통", value: 6 },
        { text: "그렇다", value: 8 },
        { text: "매우 그렇다", value: 10 }
    ];

    const [currentPage, setCurrentPage] = useState(0);
    const [answers, setAnswers] = useState(Array(questions.length).fill(null));
    const [result, setResult] = useState(null);

    const handleAnswerChange = (questionIndex, value) => {
        const newAnswers = [...answers];
        newAnswers[questionIndex] = value;
        setAnswers(newAnswers);
    };

    const handleNextPage = () => {
        if (currentPage < Math.floor(questions.length / 5)) {
            setCurrentPage(currentPage + 1);
        }
    };

    const handlePrevPage = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    const handleSubmit = async () => {

        try {
            const userSurveyData = {
                citrus: answers[0],
                fruity: answers[1],
                floral: answers[2],
                aromatic: answers[3],
                smoky: answers[4],
                spicy: answers[5],
                cotton: answers[6],
                whiteMusk: answers[7],
                aquatic: answers[8],
                amber: answers[9],
                green: answers[10],
                incense: answers[11],
                oriental: answers[12],
                earthy: answers[13],
                herbal: answers[14],
                powdery: answers[15],
                vanilla: answers[16],
                woody: answers[17],
            };

            const response = await axios.post('http://localhost:8080/survey/simple/recommend', userSurveyData);
            console.log(answers)
            setResult(response.data);
        } catch (error) {
            console.error("Error submitting survey:", error);
        }
    };

    const renderQuestions = () => {
        const startIndex = currentPage * 5;
        const endIndex = startIndex + 5;
        return questions.slice(startIndex, endIndex).map((question, qIndex) => (
            <div key={qIndex} className="simple-test-question-container">
                <div className="simple-test-question">
                    <p>{question}</p>
                </div>
                <div className="simple-test-options">
                    {options.map((option, oIndex) => (
                        <label key={oIndex} className="simple-test-label">
                            <input
                                type="radio"
                                name={`question-${startIndex + qIndex}`}
                                value={option.value}
                                checked={answers[startIndex + qIndex] === option.value}
                                onChange={() => handleAnswerChange(startIndex + qIndex, option.value)}
                            />
                            <span className={`simple-test-btn ${oIndex === 0 ? 'first' : ''} ${oIndex === options.length - 1 ? 'last' : ''}`}>
                                {option.text}
                            </span>
                        </label>
                    ))}
                </div>
            </div>
        ));
    };

    return (
        <div className="simple-test-container">
            <div className="simple-test-vertical-align">
                <div className="simple-test-btns">
                    {renderQuestions()}
                </div>
                <div className="simple-test-navigation">
                    {currentPage > 0 && <button onClick={handlePrevPage}>Previous</button>}
                    {currentPage < Math.floor(questions.length / 5) && <button onClick={handleNextPage}>Next</button>}
                    {currentPage === Math.floor(questions.length / 5) && <button onClick={handleSubmit}>Submit</button>}
                </div>
                {result && (
                    <div className="result">
                        <h3>Recommended Perfume</h3>
                        <p>{result.perfumeName}</p>
                        <p>{result.brand}</p>
                        <img src={result.imageUrl} alt={result.perfumeName} />
                    </div>
                )}
            </div>
        </div>
    );
};

export default SimpleSurvey