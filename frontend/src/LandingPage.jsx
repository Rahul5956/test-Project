import React, { useEffect, useRef, useState } from 'react';
import DonorLogin from './DonorLogin';
import NGOLogin from './NGOLogin';
import loginPage from './assets/loginPage.jpg'; // Update the image path if necessary

const LandingPage = () => {
  const [displayText, setDisplayText] = useState('');
  const typingRef = useRef({ index: 0, isClearing: false });
  const fullText = "One Donation, One Life at a Time.";
  const speed = 100; // Speed of typing animation
  const delay = 4000; // Delay after full text is displayed

  useEffect(() => {
    const typeWriter = () => {
      if (typingRef.current.isClearing) {
        if (displayText === '') {
          typingRef.current.isClearing = false;
          typingRef.current.index = 0;
          setDisplayText(fullText[typingRef.current.index]);
        } else {
          setDisplayText(prev => prev.slice(0, -1));
        }
      } else {
        if (typingRef.current.index < fullText.length) {
          setDisplayText(prev => prev + fullText[typingRef.current.index]);
          typingRef.current.index++;
        } else {
          typingRef.current.isClearing = true;
          setTimeout(() => {
            setDisplayText('');
          }, delay);
        }
      }
    };

    const timer = setInterval(typeWriter, speed);

    return () => clearInterval(timer); // Clean up interval on component unmount
  }, [displayText, fullText]);

  return (
    <div className="relative h-screen flex flex-col">
      <img src={loginPage} alt="Background" className="w-full h-1/4 object-cover" />
      <div className="flex-grow flex flex-col items-center justify-center">
        <div className="text-center mb-8">
          <h1 className="text-4xl font-bold">{displayText}</h1>
        </div>
        <div className="flex flex-col md:flex-row w-full h-3/4">
          <div className="w-full md:w-1/2 p-6 flex justify-center items-center bg-gray-100">
            <DonorLogin />
          </div>
          <div className="w-full md:w-1/2 p-6 flex justify-center items-center bg-gray-200">
            <NGOLogin />
          </div>
        </div>
      </div>
    </div>
  );
};

export default LandingPage;
