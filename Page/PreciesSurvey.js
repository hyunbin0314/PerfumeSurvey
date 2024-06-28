import React, { useState } from 'react'
import '../CSS/simpletest.scss'
import axios from 'axios';

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
    "따뜻하고 목가적인 향을 좋아하십니까?",  // 우디
    "산뜻한 아침을 연상시키는 허브 향을 좋아하십니까? ",
    "상쾌한 아침 공기와 같은 향기를 좋아하십니까? ",
    "자연의 신선한 과일 향을 좋아하십니까? ",
    "꽃들이 피어있는 호숫가에서 불어오는 상쾌한 바람의 향기를 좋아하십니까? ",
    "허브 정원에서의 휴식을 선호하십니까?",
    "캠프파이어의 따뜻한 느낌을 좋아하십니까? ",
    "이국적인 향신료의 자극적인 향을 좋아하십니까? ",
    "새로 세탁한 옷에서 나는 향기를 좋아하십니까?",
    "맑고 시원한 바람에 감싸인 부드러운 포옹을 연상시키는 향을 좋아하십니까?",
    "따뜻한 자연의 나무 향을 좋아하십니까? ",
    "신비로운 숲의 느낌을 좋아하십니까? ",
    "고요한 사원에서 맡을 수 있는 향을 좋아하십니까?",
    "달콤하고 포근한 향기를 좋아하십니까? ",
    "부드럽고 파우더리한 느낌의 향을 좋아하십니까? ",
    "강렬하고 깊은 향을 선호하십니까? ",
    "따뜻한 햇볕을 받은 나무의 향기를 좋아하십니까? ",
    "신선한 풀내음을 좋아하십니까? ",
    "활기찬 자연의 신선한 향기를 좋아하십니까? "
];

const options = [
    "매우 그렇지 않다",
    "그렇지 않다",
    "보통",
    "그렇다",
    "매우 그렇다"
];


const PreciesSurvey = () => {

    const [currentPage, setCurrentPage] = useState(0);
    const [answers, setAnswers] = useState(Array(questions.length).fill(null));

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
        // 점수 계산 로직 추가
        const scores = calculateScores(answers);

        try {
            const response = await axios.post('http://localhost:8080/survey/pre/recommend', scores);
            console.log(scores)
            console.log(response.data);
        } catch (error) {
            console.error("Error submitting survey:", error);
        }
    };

    const calculateScores = (answers) => {
        const scores = {
            citrus: 0,
            fruity: 0,
            floral: 0,
            aromatic: 0,
            smoky: 0,
            spicy: 0,
            cotton: 0,
            whiteMusk: 0,
            aquatic: 0,
            amber: 0,
            green: 0,
            incense: 0,
            oriental: 0,
            earthy: 0,
            herbal: 0,
            powdery: 0,
            vanilla: 0,
            woody: 0
        };

        answers.forEach((value, index) => {
            const score = parseInt(value, 10);

            switch (index) {
                // 향기 특성 질문 (1~18번)
                case 0: scores.citrus += score; break;
                case 1: scores.fruity += score; break;
                case 2: scores.floral += score; break;
                case 3: scores.aromatic += score; break;
                case 4: scores.smoky += score; break;
                case 5: scores.spicy += score; break;
                case 6: scores.cotton += score; break;
                case 7: scores.whiteMusk += score; break;
                case 8: scores.aquatic += score; break;
                case 9: scores.amber += score; break;
                case 10: scores.green += score; break;
                case 11: scores.incense += score; break;
                case 12: scores.oriental += score; break;
                case 13: scores.earthy += score; break;
                case 14: scores.herbal += score; break;
                case 15: scores.powdery += score; break;
                case 16: scores.vanilla += score; break;
                case 17: scores.woody += score; break;
                // 복합 질문 (19~36번)
                case 18: scores.herbal += score * 0.5; scores.citrus += score * 0.5; break;
                case 19: scores.citrus += score * 0.5; scores.aquatic += score * 0.5; break;
                case 20: scores.fruity += score * 0.5; scores.green += score * 0.5; break;
                case 21: scores.floral += score * 0.5; scores.powdery += score * 0.5; break;
                case 22: scores.aromatic += score * 0.5; scores.herbal += score * 0.5; break;
                case 23: scores.smoky += score * 0.5; scores.amber += score * 0.5; break;
                case 24: scores.spicy += score * 0.5; scores.oriental += score * 0.5; break;
                case 25: scores.cotton += score * 0.5; scores.whiteMusk += score * 0.5; break;
                case 26: scores.aquatic += score * 0.5; scores.whiteMusk += score * 0.5; break;
                case 27: scores.vanilla += score * 0.5; scores.woody += score * 0.5; break;
                case 28: scores.incense += score * 0.5; scores.earthy += score * 0.5; break;
                case 29: scores.incense += score * 0.5; scores.oriental += score * 0.5; break;
                case 30: scores.vanilla += score * 0.5; scores.cotton += score * 0.5; break;
                case 31: scores.powdery += score * 0.5; scores.floral += score * 0.5; break;
                case 32: scores.smoky += score * 0.5; scores.spicy += score * 0.5; break;
                case 33: scores.amber += score * 0.5; scores.woody += score * 0.5; break;
                case 34: scores.green += score * 0.5; scores.earthy += score * 0.5; break;
                case 35: scores.aromatic += score * 0.5; scores.fruity += score * 0.5; break;
                default: break;
            }
        });

        return scores;
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
                                value={oIndex + 1}
                                checked={answers[startIndex + qIndex] === (oIndex + 1).toString()}
                                onChange={() => handleAnswerChange(startIndex + qIndex, (oIndex + 1).toString())}
                            />
                            <span className={`simple-test-btn ${oIndex === 0 ? 'first' : ''} ${oIndex === options.length - 1 ? 'last' : ''}`}>
                                {option}
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
            </div>
        </div>
    );
};


export default PreciesSurvey