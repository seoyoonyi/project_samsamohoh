import React from 'react';
import Head from "next/head";
import Button from '../components/button';
import Footer from './footer';

const Modal = () => {
    return (
        <>
         <Head>
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"
        />
        <link rel="icon" type="image/x-icon" href="/images/favicon.ico" />
        <title>모달모달</title>
      </Head>
        <div className="modal-wrap">
            modalmodala
        </div>
        <Button/>
        <Footer/>
        </>
    );
};

export default Modal;