import React, { useEffect, useState } from 'react'
import '../CSS/stlyes.scss'
import { Link } from 'react-router-dom'

const Main = () => {
    const IMAGES = {
        HOME: 'https://www.star-candle.com/shopimages/khkassi/0200010000032.jpg?1571729829',
        ABOUT: 'https://www.soapmold.co.kr/shopimages/ym2941/mobile/9/689_represent?1601011181',
    };

    useEffect(() => {
        const slides = document.querySelectorAll('.mainpage-flex-slide');
        slides.forEach(slide => {
            slide.addEventListener('mouseenter', () => {
                const title = slide.querySelector('.mainpage-flex-title');
                const about = slide.querySelector('.mainpage-flex-about');
                if (title) {
                    title.style.transform = 'rotate(0deg)';
                    title.style.top = '10%';
                }
                if (about) {
                    about.style.opacity = '1';
                }
            });

            slide.addEventListener('mouseleave', () => {
                const title = slide.querySelector('.mainpage-flex-title');
                const about = slide.querySelector('.mainpage-flex-about');
                if (title) {
                    title.style.transform = 'rotate(90deg)';
                    title.style.top = '15%';
                }
                if (about) {
                    about.style.opacity = '0';
                }
            });
        });
    }, []);

    return (
        <div className="mainpage-flex-container">
            <div className="mainpage-flex-slide mainpage-home" style={{ backgroundImage: `url(${IMAGES.HOME})`, backgroundSize: '1150px 920px', backgroundPosition: 'left', backgroundRepeat: 'no-repeat' }}>
                <Link to={'/simpletest'}>
                    <div className="mainpage-flex-title mainpage-flex-title-home"></div>
                    <div className="mainpage-flex-about mainpage-flex-about-home">
                        <p>Simple Test</p>
                    </div>
                </Link>
            </div>
            <div className="mainpage-flex-slide mainpage-about" style={{ backgroundImage: `url(${IMAGES.ABOUT})`, backgroundSize: '1150px 920px', backgroundPosition: 'right', backgroundRepeat: 'no-repeat' }}>
                <div className="mainpage-flex-title"></div>
                <div className="mainpage-flex-about">
                    <p>Precise Test</p>
                </div>
            </div>
        </div>
    );
};

export default Main;