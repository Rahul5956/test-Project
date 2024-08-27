import React from 'react';
import DonorLogin from './DonorLogin';
import NGOLogin from './NGOLogin';

const AuthPage = () => {
  return (
    <div className="flex flex-col md:flex-row h-screen">
      <div className="w-full md:w-1/2 p-6 flex justify-center items-center bg-gray-100">
        <DonorLogin />
      </div>
      <div className="w-full md:w-1/2 p-6 flex justify-center items-center bg-gray-200">
        <NGOLogin />
      </div>
    </div>
  );
};

export default AuthPage;
