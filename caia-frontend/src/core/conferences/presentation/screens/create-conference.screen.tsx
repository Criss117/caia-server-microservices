"use client";

import BackButton from "@/core/shared/components/ui/backbutton";
import { Button } from "@/core/shared/components/ui/button";
import { Input } from "@/core/shared/components/ui/input";
import { Label } from "@/core/shared/components/ui/label";
import useCreateConference from "../../application/hooks/use.create-conference";

const CreateConferenceScreen = () => {
  const { data, setNombre, setDescripcion, onSubmit } = useCreateConference();

  return (
    <>
      <BackButton />
      <form
        className="flex flex-col w-3/4 md:w-1/2 space-y-5 mt-10 mx-auto"
        onSubmit={(e) => {
          e.preventDefault();
          onSubmit();
        }}
      >
        <fieldset className="flex flex-col space-y-5 border p-5 rounded-lg shadow-md">
          <Label className="text-xl font-bold">
            Descripción general de la conferencia
          </Label>
          <Label htmlFor="conference-name" className="space-y-2">
            Nombre de la conferencia
            <Input
              type="text"
              id="conference-name"
              placeholder="Nombre de la conferencia"
              className="border-slate-400"
              value={data.name}
              onChange={(e) => setNombre(e.target.value)}
            />
          </Label>
          <Label htmlFor="conference-description">
            Descripción de la conferencia
            <Input
              type="text"
              id="conference-description"
              placeholder="Descripción de la conferencia"
              className="border-slate-400"
              value={data.description}
              onChange={(e) => setDescripcion(e.target.value)}
            />
          </Label>
        </fieldset>
        <fieldset>
          <Button variant="default" className="w-full" type="submit">
            Crear conferencia
          </Button>
        </fieldset>
      </form>
    </>
  );
};

export default CreateConferenceScreen;
