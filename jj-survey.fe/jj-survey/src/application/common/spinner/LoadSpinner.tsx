import React from 'react';
import { usePromiseTracker } from 'react-promise-tracker';
import Loader from 'react-loader-spinner';

import 'react-loader-spinner/dist/loader/css/react-spinner-loader.css';
import './LoadSpinner.scss';

export default function LoadSpinner() {
    const { promiseInProgress } = usePromiseTracker();

    const types: string[] = [];
    // types.push("Audio");
    // types.push("BallTriangle");
    // types.push("Bars");
    // types.push("Circles");
    // types.push("Grid");
    // types.push("Hearts");
    types.push("Oval");
    // types.push("Puff");
    // types.push("Rings");
    // types.push("TailSpin");
    // types.push("ThreeDots");
    // types.push("Watch");
    // types.push("RevolvingDot");
    // types.push("Triangle");
    // types.push("Plane");
    // types.push("MutatingDots");

    if (!promiseInProgress) {
        return null;
    }

    const typeIndex: number = Math.floor((Math.random() * 100) % types.length);

    return (
        <div className="load-spinner-container">
            <div className="load-background"></div>
            <div className="load-spinner">
                <Loader type={types[typeIndex]} color="#2BAD60" height={100} width={100} autoFocus />
            </div>
        </div>
    );
}
