import {useState} from "react";

export default function AuthorForm() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [dob, setDob] = useState('');
    const [gender, setGender] = useState('MALE');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [country, setCountry] = useState('');

    const handleSubmit = async (evt) => {
        console.log('event', evt);
        evt.preventDefault();
        evt.stopPropagation();

        const body = {
            firstName,
            lastName,
            email,
            dob,
            gender,
           address: {
               city,
               state,
               country,
           }
        }
        console.log(body);
        const rez = await fetch('http://localhost:8080/api/v1/authors', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(body)
        });
    }

    const handleFirstNameChange = (e) => setFirstName(e.target.value);
    const handleLastNameChange = (e) => setLastName(e.target.value);
    const handleEmailChange = (e) => setEmail(e.target.value);
    const handleDobChange = (e) => setDob(e.target.value);
    const handleGenderChange = (e) => setGender(e.target.value);
    const handleCityChange = (e) => setCity(e.target.value);
    const handleStateChange = (e) => setState(e.target.value);
    const handleCountryChange = (e) => setCountry(e.target.value);

    return (
        <div className="create">
            <h2>Add a New Author</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="firstName">First name:</label>
                <input
                    id="firstName"
                    type="text"
                    value={firstName}
                    onChange={handleFirstNameChange}
                />
                <label htmlFor="lastName">Last name:</label>
                <input
                    id="lastName"
                    type="text"
                    value={lastName}
                    onChange={handleLastNameChange}
                />
                <label htmlFor="email">Email:</label>
                <input
                    id="email"
                    type="text"
                    value={email}
                    onChange={handleEmailChange}
                />
                <label htmlFor="dob">Date of Birth:</label>
                <input
                    id="dob"
                    type="text"
                    value={dob}
                    onChange={handleDobChange}
                />
                <label htmlFor="gender">Gender:</label>
                <select
                    id="gender"
                    value={gender}
                    onChange={handleGenderChange}
                    >
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                </select>
                <label htmlFor="city">City:</label>
                <input
                    id="city"
                    type="text"
                    value={city}
                    onChange={handleCityChange}
                />
                <label htmlFor="state">State:</label>
                <input
                    id="state"
                    type="text"
                    value={state}
                    onChange={handleStateChange}
                />
                <label htmlFor="country">Country:</label>
                <input
                    id="country"
                    type="text"
                    value={country}
                    onChange={handleCountryChange}
                />
                <button type="submit">Add author</button>
            </form>
        </div>

    )
}