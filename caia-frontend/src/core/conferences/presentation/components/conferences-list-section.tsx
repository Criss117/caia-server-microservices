import ConferenceCard from "@/core/conferences/presentation/components/conference-card";
import findConferencesAction from "../../data/actions/find-conferences";

const ConferencesListSection = async () => {
  const { data } = await findConferencesAction(null);

  return (
    <section aria-label="Conferences List Section" className="mx-auto">
      <header className="border-y-2 py-5">
        <div className="w-[80%] mx-auto">
          <h3 className="font-bold text-xl text-center md:text-start">
            Conferencias Académicas: Descubre y Participa
          </h3>
        </div>
      </header>
      <div className="w-[80%] mx-auto grid md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-5 gap-y-10 my-10">
        {data?.map((conference, index) => (
          <ConferenceCard conference={conference} key={index} />
        ))}
      </div>
    </section>
  );
};

export default ConferencesListSection;
