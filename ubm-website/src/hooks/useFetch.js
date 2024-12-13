import {useEffect, useState} from "react";


export default function useFetch(url) {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        const loadData = async () => {
            try {
                const rez = await fetch(url);
                if (!rez.ok) {
                    throw Error('could not fetch the data for that resource');
                }
                const data = await rez.json();
                setData(data);

            } catch (err) {
                setError(err.message);
            }
            setLoading(false);
        }

        loadData();

    }, [url]);

    return {data, loading, error};
}