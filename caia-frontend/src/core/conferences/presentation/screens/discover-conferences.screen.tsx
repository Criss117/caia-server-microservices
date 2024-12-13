import { Input } from "@/core/shared/components/ui/input";
import ConferencesList from "../components/conferences-list";

const DiscoverConferencesScreen = () => {
  return (
    <>
      <header>
        <h2 className="text-4xl font-black">Descubre Conferencias</h2>
      </header>
      <div className="flex justify-between mt-10">
        <Input
          placeholder="Buscar Conferencias"
          type="text"
          className="w-1/3 border-lighttext-100"
        />
      </div>
      <div>
        <ConferencesList onDiscover />
      </div>
    </>
  );
};

export default DiscoverConferencesScreen;
